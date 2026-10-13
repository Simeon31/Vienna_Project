import type { Warehouse, WarehouseInput } from '../types';

export async function getWarehouses(): Promise<Warehouse[]> {
    const response = await fetch('/api/warehouses');

    if (!response.ok) {
        throw new Error(`Failed to load warehouses (${response.status})`);
    }

    return response.json();
}

/** Thrown when the backend refuses to deactivate because the warehouse still has active shipments (409). */
export class WarehouseHasActiveShipmentsError extends Error {}

export async function activateWarehouse(id: number): Promise<Warehouse> {
    const response = await fetch(`/api/warehouses/${id}/activate`, { method: 'PATCH' });

    if (!response.ok) {
        throw new Error(`Failed to activate warehouse (${response.status})`);
    }

    return response.json();
}

export async function deactivateWarehouse(id: number): Promise<Warehouse> {
    // throw new WarehouseHasActiveShipmentsError('test');
    const response = await fetch(`/api/warehouses/${id}/deactivate`, { method: 'PATCH' });

    if (response.status === 409) {
        throw new WarehouseHasActiveShipmentsError(await response.text());
    }
    if (!response.ok) {
        throw new Error(`Failed to deactivate warehouse (${response.status})`);
    }

    return response.json();
}

export async function createWarehouse(input: WarehouseInput): Promise<Warehouse> {
    const response = await fetch('/api/warehouses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(input),
    });

    if (response.status === 400) {
        // The backend explains what is wrong, e.g. "Warehouse name is required"
        throw new Error(await response.text());
    }
    if (!response.ok) {
        throw new Error(`Failed to create warehouse (${response.status})`);
    }

    return response.json();
}

/** Thrown when the backend answers 404 for a warehouse id. */
export class WarehouseNotFoundError extends Error {}

export async function getWarehouse(id: number): Promise<Warehouse> {
    const response = await fetch(`/api/warehouses/${id}`);

    if (response.status === 404) {
        throw new WarehouseNotFoundError(`Warehouse ${id} not found`);
    }
    if (!response.ok) {
        throw new Error(`Failed to load warehouse (${response.status})`);
    }

    return response.json();
}

export async function updateWarehouse(id: number, input: WarehouseInput): Promise<Warehouse> {
    const response = await fetch(`/api/warehouses/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(input),
    });

    if (response.status === 400) {
        throw new Error(await response.text());
    }
    if (response.status === 404) {
        throw new WarehouseNotFoundError(`Warehouse ${id} not found`);
    }
    if (!response.ok) {
        throw new Error(`Failed to update warehouse (${response.status})`);
    }

    return response.json();
}