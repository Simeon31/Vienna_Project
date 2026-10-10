export interface DashboardStats {
    totalAccounts: number;
    activeAccounts: number;
    totalWarehouses: number;
    activeWarehouses: number;
    warehousesNearCapacity: number;
}

export interface WarehouseCapacity {
    id: number;
    name: string;
    location: string;
    occupiedPercent: number;
    reservedPercent: number;
}