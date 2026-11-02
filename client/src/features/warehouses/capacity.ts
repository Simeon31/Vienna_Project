import type { Warehouse, WarehouseUtilization } from './types';
import type { BadgeVariant } from '../../components/ui/Badge';

export const WARNING_THRESHOLD = 75;
export const CRITICAL_THRESHOLD = 90;

export interface CapacityStatus {
    label: string;
    variant: BadgeVariant;
}

export function getCapacityStatus(committedPercent: number): CapacityStatus {
    if (committedPercent >= CRITICAL_THRESHOLD) {
        return { label: 'Critical', variant: 'danger' };
    }
    if (committedPercent >= WARNING_THRESHOLD) {
        return { label: 'Warning', variant: 'warning' };
    }
    return { label: 'Normal', variant: 'neutral' };
}

// Sample utilization until inventory is connected.
// Based on the warehouse id, so the same warehouse shows the same values on every page.
const SAMPLE_UTILIZATION: WarehouseUtilization[] = [
    { occupiedPercent: 84, reservedPercent: 8 },
    { occupiedPercent: 70, reservedPercent: 8 },
    { occupiedPercent: 50, reservedPercent: 5 },
    { occupiedPercent: 25, reservedPercent: 7 },
];

export function getSampleUtilization(warehouseId: number): WarehouseUtilization {
    return SAMPLE_UTILIZATION[warehouseId % SAMPLE_UTILIZATION.length];
}

export function getCommittedPercent(utilization: WarehouseUtilization): number {
    return utilization.occupiedPercent + utilization.reservedPercent;
}

export function isNearCapacity(warehouse: Warehouse): boolean {
    return (
        warehouse.active &&
        getCommittedPercent(getSampleUtilization(warehouse.id)) >= WARNING_THRESHOLD
    );
}