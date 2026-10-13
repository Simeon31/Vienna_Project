export interface Warehouse {
    id: number;
    name: string;
    address: string;
    latitude: number;
    longitude: number;
    capacity: number;
    active: boolean;
}

export interface WarehouseUtilization {
    occupiedPercent: number;
    reservedPercent: number;
}

export interface PendingStatusChange {
    warehouse: Warehouse;
    action: 'activate' | 'deactivate';
}

/** Raw form input. Strings, because an empty field is not the same as 0. */
export interface WarehouseFormValues {
    name: string;
    address: string;
    latitude: string;
    longitude: string;
    capacity: string;
}

/** What the backend expects when creating or updating a warehouse. */
export interface WarehouseInput {
    name: string;
    address: string;
    latitude: number;
    longitude: number;
    capacity: number;
}