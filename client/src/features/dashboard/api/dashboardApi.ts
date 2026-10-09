import type { DashboardStats } from '../types';
import { getWarehouses } from '../../warehouses/api/warehousesApi';

// Mock values until the backend provides them
const MOCK_TOTAL_ACCOUNTS = 248;
const MOCK_ACTIVE_ACCOUNTS = 221;
const MOCK_WAREHOUSES_NEAR_CAPACITY = 2;

export async function getDashboardStats(): Promise<DashboardStats> {
    const warehouses = await getWarehouses();

    return {
        totalAccounts: MOCK_TOTAL_ACCOUNTS,
        activeAccounts: MOCK_ACTIVE_ACCOUNTS,
        totalWarehouses: warehouses.length,
        activeWarehouses: warehouses.filter((warehouses) => warehouses.active).length,
        warehousesNearCapacity: MOCK_WAREHOUSES_NEAR_CAPACITY,
    };
}