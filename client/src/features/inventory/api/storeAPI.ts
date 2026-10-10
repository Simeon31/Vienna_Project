import type { Store } from '../types';

export async function getStores(): Promise<Store[]> {
    const response = await fetch('/api/inventory');

    if (!response.ok) {
        throw new Error(`Failed to load stores (${response.status})`);
    }

    return response.json();
}