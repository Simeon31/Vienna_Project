
export interface Shipment {
    id: number | null;
    warehouseId: number;
    orderStatus: string;
    expectedArrival: string | null;
    createdAt: string | null;
}
