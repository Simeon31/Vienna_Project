import { BrowserRouter, Route, Routes } from 'react-router';
import AppLayout from './components/layout/AppLayout';
import DashboardPage from './features/dashboard/pages/DashboardPage';
import WarehousesPage from './features/warehouses/pages/WarehousesPage';
import StorePage from "./features/inventory/pages/StorePage.tsx";
import ShipmentsPage from './features/shipments/pages/ShipmentsPage';

export default function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route element={<AppLayout />}>
            <Route index element={<DashboardPage />} />
            <Route path="warehouses" element={<WarehousesPage />} />
            <Route path="inventory" element={<StorePage />} />
              <Route path="shipments" element={<ShipmentsPage />} />
            <Route path="inventory" element={<h1>Inventory</h1>} />
            <Route path="*" element={<h1>Page not found</h1>} />
          </Route>
        </Routes>
      </BrowserRouter>
  );
}