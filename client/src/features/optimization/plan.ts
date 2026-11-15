import type { BadgeVariant } from '../../components/ui/Badge';
import type { OptimizationRun, RunStatus } from './types';

/** Label and badge colour for each run status. */
export const RUN_STATUS: Record<RunStatus, { label: string; variant: BadgeVariant }> = {
    PENDING: { label: 'Pending', variant: 'warning' },
    ACCEPTED: { label: 'Accepted', variant: 'success' },
    REJECTED: { label: 'Rejected', variant: 'neutral' },
};

const euroFormat = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'EUR',
    maximumFractionDigits: 0,
});

const kmFormat = new Intl.NumberFormat('en-US', { maximumFractionDigits: 1 });

/** Formats an amount as whole euros, e.g. "€1,250". */
export function formatEuro(amount: number): string {
    return euroFormat.format(amount);
}

/** Formats a distance, e.g. "31 km" or "6.4 km". */
export function formatKm(distance: number): string {
    return `${kmFormat.format(distance)} km`;
}

/** All orders in the wave, assigned or not. */
export function getOrderCount(run: OptimizationRun): number {
    return run.assignments.length + run.unmetOrders.length;
}

/** How much the plan saves compared to the baseline strategy. */
export function getSavings(run: OptimizationRun): number {
    return run.baselineCost - run.optimizedCost;
}

/** Savings as a percentage of the baseline cost. */
export function getSavingsPercent(run: OptimizationRun): number {
    return run.baselineCost > 0 ? (getSavings(run) / run.baselineCost) * 100 : 0;
}

/** Number of different warehouses that supply at least one order. */
export function getWarehouseCount(run: OptimizationRun): number {
    return new Set(run.assignments.map((assignment) => assignment.warehouseId)).size;
}

/**
 * A plan can be accepted while it is pending and at least one order can be supplied.
 * Orders that cannot be supplied stay pending for the next wave.
 */
export function canAccept(run: OptimizationRun): boolean {
    return run.status === 'PENDING' && run.assignments.length > 0;
}

/** A plan can be rejected while it is pending, even if no order can be supplied. */
export function canReject(run: OptimizationRun): boolean {
    return run.status === 'PENDING';
}