import { useState } from 'react';
import {
    WarehouseHasActiveShipmentsError,
    activateWarehouse,
    deactivateWarehouse,
} from '../api/warehousesApi';
import type { PendingStatusChange, Warehouse } from '../types';

type StatusAction = PendingStatusChange['action'];

/**
 * Shared flow for activating and deactivating a warehouse:
 * confirmation dialog, PATCH request, 409 handling and saving state.
 * `onChanged` is called with the updated warehouse after a successful change.
 */
export function useWarehouseStatusChange(
    onChanged: (updated: Warehouse, action: StatusAction) => void,
) {
    const [pendingChange, setPendingChange] = useState<PendingStatusChange | null>(null);
    const [blockedWarehouse, setBlockedWarehouse] = useState<Warehouse | null>(null);
    const [saving, setSaving] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const open = (warehouse: Warehouse) => {
        setError(null);
        setPendingChange({ warehouse, action: warehouse.active ? 'deactivate' : 'activate' });
    };

    const cancel = () => {
        // Don't close while the request is still running
        if (!saving) setPendingChange(null);
    };

    const confirm = async () => {
        if (!pendingChange) return;
        const { warehouse, action } = pendingChange;

        setSaving(true);
        setError(null);

        try {
            const updated =
                action === 'activate'
                    ? await activateWarehouse(warehouse.id)
                    : await deactivateWarehouse(warehouse.id);

            setPendingChange(null);
            onChanged(updated, action);
        } catch (caught) {
            if (caught instanceof WarehouseHasActiveShipmentsError) {
                setPendingChange(null);
                setBlockedWarehouse(warehouse);
            } else {
                setError('The status could not be changed. Please try again.');
            }
        } finally {
            setSaving(false);
        }
    };

    const closeBlocked = () => setBlockedWarehouse(null);

    return { pendingChange, blockedWarehouse, saving, error, open, cancel, confirm, closeBlocked };
}

export type WarehouseStatusChange = ReturnType<typeof useWarehouseStatusChange>;