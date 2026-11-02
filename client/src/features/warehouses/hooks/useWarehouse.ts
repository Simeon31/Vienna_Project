import { useEffect, useState } from 'react';
import { WarehouseNotFoundError, getWarehouse } from '../api/warehousesApi';
import type { Warehouse } from '../types';

/** Loads one warehouse by the id from the URL. Handles invalid ids, 404 and other errors. */
export function useWarehouse(idParam: string | undefined) {
    const warehouseId = Number(idParam);
    const invalidId = !Number.isInteger(warehouseId) || warehouseId <= 0;

    const [warehouse, setWarehouse] = useState<Warehouse | null>(null);
    const [loadError, setLoadError] = useState<'not-found' | 'failed' | null>(null);

    useEffect(() => {
        if (invalidId) return;
        let ignore = false;

        getWarehouse(warehouseId)
            .then((data) => {
                if (!ignore) setWarehouse(data);
            })
            .catch((caught) => {
                if (!ignore) setLoadError(caught instanceof WarehouseNotFoundError ? 'not-found' : 'failed');
            });

        return () => {
            ignore = true;
        };
    }, [warehouseId, invalidId]);

    return {
        warehouseId,
        warehouse,
        setWarehouse,
        notFound: invalidId || loadError === 'not-found',
        failed: loadError === 'failed',
    };
}