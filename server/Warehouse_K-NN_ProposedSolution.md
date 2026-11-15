# **Warehouse** 

_K-NN Optimization algorithm Research date: 6 November 2026_ 

_By: Simeon Markov_ 

## **1 PROBLEM DEFINITION** 

The main problem we’re facing is how we can ensure that each store gets supplied from all the existing warehouses in an optimal way, or in other words: 

<mark>For each store, which existing warehouse is closest, and how far is the outbound journey?</mark> 

The problem could be expressed with the following mathematical formulation: 



<!-- Start of picture text -->
w*(s) = arg min c(w, s)<br>weW,<br><!-- End of picture text -->

_Figure 1: Nearest-warehouse decision_ 

### **Breakdown:** 

**Ws** : Set of warehouses eligible to supply store 𝑠. 

**c(w,s)** : The chosen outbound distance (travel time). 

### **Possible Implementations:** 

- ➢ **Mapping pairs:** One way is to calculate for each possible warehouse-pair, but the problem with that approach is that the calculation workload increases in θ(W * S) (e.g. if we have 1000 stores and 10 warehouses that’s already 10 000 possible pairs). 

- ➢ **Geographic proximity:** Use a spherical distance metric to rank locations on the Earth's surface. Haversine implementation computes great-circle angular distance. 

**_Note:_** _For this application, “k-NN” means nearest-neighbor retrieval, not a classifier or a model that must learn from historical labels._ 

## **2 TECHNICAL VIABILITY** 

- A practical option is using **BallTree** . Building the index from warehouse coordinates, then 

- query it using store coordinates. Our inputs would be **latitude & longitude** in radians, and the output in angular distance that can be multiplied by Earth's radius to obtain meters or kilometers. We can save the locations in our database (PostgreSQL) as it offers index-assisted nearest- 

neighbor ordering and nearest-neighbor lookup for each store via the help of **PostGIS.** As an output. At the end, we can return **store_id** , **warehouse_id** , candidate rank & geographic distance. 

**Constraint:** Our current limitation is that the geographic spatial proximity works by finding the shortest route, but the shortest route does not necessarily mean the fastest one. This is a different problem that requires different approach (e.g. tracking live road traffic). 

### **_VISUAL REPRESENTATION OF THE IDEA USING BALLTREES_** 



<!-- Start of picture text -->
Warehouse D<br>Individual  Warehouse C<br>store<br>Warehouse A<br>Individual<br>store<br>      Warehouse B<br>Manufacturer A<br>Manufacturer B<br><!-- End of picture text -->

