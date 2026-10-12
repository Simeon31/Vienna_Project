import { BrowserRouter, Route, Routes } from 'react-router';
import AppLayout from './components/layout/AppLayout';
import DashboardPage from './features/dashboard/pages/DashboardPage';
import WarehousesPage from './features/warehouses/pages/WarehousesPage';
<<<<<<< HEAD
import StorePage from "./features/inventory/pages/StorePage.tsx";
=======
import ShipmentsPage from './features/shipments/pages/ShipmentsPage';
>>>>>>> 4738d870cc2f167a0aaf9396dcf5a850238e30cc

export default function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route element={<AppLayout />}>
            <Route index element={<DashboardPage />} />
            <Route path="warehouses" element={<WarehousesPage />} />
<<<<<<< HEAD
            <Route path="shipments" element={<h1>Shipments</h1>} />
            <Route path="inventory" element={<StorePage />} />
=======
              <Route path="shipments" element={<ShipmentsPage />} />
            <Route path="inventory" element={<h1>Inventory</h1>} />
>>>>>>> 4738d870cc2f167a0aaf9396dcf5a850238e30cc
            <Route path="*" element={<h1>Page not found</h1>} />
          </Route>
        </Routes>
      </BrowserRouter>
  );
}