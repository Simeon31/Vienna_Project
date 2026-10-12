import type { DashboardStats, WarehouseCapacity } from '../types';
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

// Sample utilization until inventory and shipments are connected
const SAMPLE_UTILIZATION = [
    { occupied: 84, reserved: 8 },
    { occupied: 70, reserved: 8 },
    { occupied: 50, reserved: 5 },
    { occupied: 25, reserved: 7 },
];

export async function getWarehouseCapacities(): Promise<WarehouseCapacity[]> {
    const warehouses = await getWarehouses();

    return warehouses
        .filter((warehouse) => warehouse.active)
        .map((warehouse, index) => {
            const sample = SAMPLE_UTILIZATION[index % SAMPLE_UTILIZATION.length];
            return {
                id: warehouse.id,
                name: warehouse.name,
                location: warehouse.address,
                occupiedPercent: sample.occupied,
                reservedPercent: sample.reserved,
            };
        })
        .sort(
            (a, b) =>
                b.occupiedPercent + b.reservedPercent - (a.occupiedPercent + a.reservedPercent),
        );
}