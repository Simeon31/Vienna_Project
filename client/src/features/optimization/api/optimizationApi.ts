import type { OptimizationRun } from '../types';

/** Thrown when a decision is made on a run that was already accepted or rejected. */
export class OptimizationRunNotPendingError extends Error {}

// Sample data until the optimizer backend exists (US 5.1).
// The plan cost is the sum of the assignment costs, so the numbers stay consistent.
// Sample data until the optimizer backend exists (US 5.1).
// The plan cost is the sum of the transport costs, so the numbers stay consistent.
const SAMPLE_RUN: OptimizationRun = {
    id: 'RUN-2026-009',
    strategy: 'MILP',
    baselineCost: 560,
    optimizedCost: 435,
    status: 'PENDING',
    decidedAt: null,
    rejectionReason: null,
    assignments: [
        { orderId: 'ORD-2026-0128', storeName: 'Wels City', warehouseId: 1, warehouseName: 'Linz Hub', distanceKm: 31, transportCost: 170 },
        { orderId: 'ORD-2026-0127', storeName: 'Leoben Center', warehouseId: 2, warehouseName: 'Graz South', distanceKm: 64, transportCost: 120 },
        { orderId: 'ORD-2026-0126', storeName: 'Vienna Mariahilf', warehouseId: 3, warehouseName: 'Vienna Central', distanceKm: 6, transportCost: 145 },
    ],
    unmetOrders: [
        { orderId: 'ORD-2026-0129', storeName: 'Salzburg Old Town', reason: 'Not enough stock in any active warehouse' },
    ],
};

// Working copy, so a decision stays visible while the app is open (resets on page reload)
let sampleRun: OptimizationRun = structuredClone(SAMPLE_RUN);

/** Loads the latest optimization run that needs a manager decision. */
export async function getLatestOptimizationRun(): Promise<OptimizationRun> {
    return structuredClone(sampleRun);
}

/** Accepts the run, so its assignments become active (US 6.2). */
export async function acceptOptimizationRun(id: string): Promise<OptimizationRun> {
    if (id !== sampleRun.id) {
        throw new Error(`Optimization run ${id} not found`);
    }
    if (sampleRun.status !== 'PENDING') {
        throw new OptimizationRunNotPendingError(`Optimization run ${id} was already decided`);
    }

    sampleRun = { ...sampleRun, status: 'ACCEPTED', decidedAt: new Date().toISOString() };
    return structuredClone(sampleRun);
}