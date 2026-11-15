import type { BadgeVariant } from '../../components/ui/Badge';
import type { AssignmentResult, OptimizationRun, RunStatus } from './types';

/** Label and badge colour for each run status. */
export const RUN_STATUS: Record<RunStatus, { label: string; variant: BadgeVariant }> = {
    PENDING: { label: 'Pending', variant: 'warning' },
    ACCEPTED: { label: 'Accepted', variant: 'success' },
    REJECTED: { label: 'Rejected', variant: 'neutral' },
};

/** Label and badge colour for each assignment result. */
export const ASSIGNMENT_RESULT: Record<AssignmentResult, { label: string; variant: BadgeVariant }> = {
    RECOMMENDED: { label: 'Recommended', variant: 'success' },
    INSUFFICIENT: { label: 'Insufficient', variant: 'danger' },
};

const euroFormat = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'EUR',
    maximumFractionDigits: 0,
});

/** Formats an amount as whole euros, e.g. "€1,250". */
export function formatEuro(amount: number): string {
    return euroFormat.format(amount);
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

/** A plan can only be accepted if every order is fully covered by a recommended warehouse. */
export function isPlanValid(run: OptimizationRun): boolean {
    return (
        run.assignments.length > 0 &&
        run.assignments.every(
            (assignment) => assignment.result === 'RECOMMENDED' && assignment.coveragePercent === 100,
        )
    );
}