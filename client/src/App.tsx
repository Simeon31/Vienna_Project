import { BrowserRouter, Route, Routes } from 'react-router';
import AppLayout from './components/layout/AppLayout';
import DashboardPage from './features/dashboard/pages/DashboardPage';
import WarehousesPage from './features/warehouses/pages/WarehousesPage';
import StorePage from "./features/inventory/pages/StorePage.tsx";
import ShipmentsPage from './features/shipments/pages/ShipmentsPage';
import AddWarehousePage from './features/warehouses/pages/AddWarehousePage';
import EditWarehousePage from './features/warehouses/pages/EditWarehousePage';
import WarehouseDetailPage from './features/warehouses/pages/WarehouseDetailPage';

export default function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route element={<AppLayout />}>
            <Route index element={<DashboardPage />} />
            <Route path="warehouses" element={<WarehousesPage />} />
              <Route path="warehouses/new" element={<AddWarehousePage />} />
              <Route path="warehouses/:id" element={<WarehouseDetailPage />} />
              <Route path="warehouses/:id/edit" element={<EditWarehousePage />} />
            <Route path="inventory" element={<StorePage />} />
              <Route path="shipments" element={<ShipmentsPage />} />
            <Route path="*" element={<h1>Page not found</h1>} />
          </Route>
        </Routes>
      </BrowserRouter>
  );
}