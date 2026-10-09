import type { DashboardStats } from '../types';

// Mock data until the backend endpoints exist
const MOCK_STATS: DashboardStats = {
    totalAccounts: 248,
    activeAccounts: 221,
    totalWarehouses: 16,
    activeWarehouses: 14,
    warehousesNearCapacity: 2,
};

export async function getDashboardStats(): Promise<DashboardStats> {
    // Simulates network delay so the loading state is visible
    await new Promise((resolve) => setTimeout(resolve, 500));
    return MOCK_STATS;
}