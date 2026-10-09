import { BrowserRouter, Route, Routes } from 'react-router';
import DashboardPage from './features/dashboard/pages/DashboardPage';
import WarehousesPage from './features/warehouses/pages/WarehousesPage';

export default function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route index element={<DashboardPage />} />
          <Route path="warehouses" element={<WarehousesPage />} />
          <Route path="shipments" element={<h1>Shipments</h1>} />
          <Route path="inventory" element={<h1>Inventory</h1>} />
          <Route path="*" element={<h1>Page not found</h1>} />
        </Routes>
      </BrowserRouter>
  );
}