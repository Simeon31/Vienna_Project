import { BrowserRouter, Route, Routes } from 'react-router';
import AppLayout from './components/layout/AppLayout';
import DashboardPage from './features/dashboard/pages/DashboardPage';
import WarehousesPage from './features/warehouses/pages/WarehousesPage';
import StorePage from "./features/inventory/pages/StorePage.tsx";

export default function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route element={<AppLayout />}>
            <Route index element={<DashboardPage />} />
            <Route path="warehouses" element={<WarehousesPage />} />
            <Route path="shipments" element={<h1>Shipments</h1>} />
            <Route path="inventory" element={<StorePage />} />
            <Route path="*" element={<h1>Page not found</h1>} />
          </Route>
        </Routes>
      </BrowserRouter>
  );
}