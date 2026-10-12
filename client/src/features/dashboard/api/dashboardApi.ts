import type { AccountDistribution, DashboardStats, WarehouseCapacity } from '../types';
import { getWarehouses } from '../../warehouses/api/warehousesApi';
import {WARNING_THRESHOLD, getCommittedPercent, getSampleUtilization,} from '../../warehouses/capacity';

// Mock values until the accounts backend is available
const MOCK_TOTAL_ACCOUNTS = 248;
const MOCK_ACTIVE_ACCOUNTS = 221;

export async function getDashboardStats(): Promise<DashboardStats> {
    const warehouses = await getWarehouses();
    const activeWarehouses = warehouses.filter((warehouse) => warehouse.active);

    return {
        totalAccounts: MOCK_TOTAL_ACCOUNTS,
        activeAccounts: MOCK_ACTIVE_ACCOUNTS,
        totalWarehouses: warehouses.length,
        activeWarehouses: activeWarehouses.length,
        warehousesNearCapacity: activeWarehouses.filter(
            (warehouse) =>
                getCommittedPercent(getSampleUtilization(warehouse.id)) >= WARNING_THRESHOLD,).length,
    };
}

export async function getWarehouseCapacities(): Promise<WarehouseCapacity[]> {
    const warehouses = await getWarehouses();

    return warehouses
        .filter((warehouse) => warehouse.active)
        .map((warehouse) => {
            const utilization = getSampleUtilization(warehouse.id);
            return {
                id: warehouse.id,
                name: warehouse.name,
                location: warehouse.address,
                ...utilization,
            };
        })
        .sort((a, b) => getCommittedPercent(b) - getCommittedPercent(a));
}

// Mock values until the accounts backend is available
const MOCK_ACCOUNT_DISTRIBUTION: AccountDistribution = {
    managers: 48,
    employees: 200,
};

export async function getAccountDistribution(): Promise<AccountDistribution> {
    return MOCK_ACCOUNT_DISTRIBUTION;
}