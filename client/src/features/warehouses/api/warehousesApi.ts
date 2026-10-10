import type { Warehouse } from '../types';

export async function getWarehouses(): Promise<Warehouse[]> {
    const response = await fetch('/api/warehouses');

    if (!response.ok) {
        throw new Error(`Failed to load warehouses (${response.status})`);
    }

    return response.json();
}