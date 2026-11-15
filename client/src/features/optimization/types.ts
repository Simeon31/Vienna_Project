/** Decision state of an optimization run. */
export type RunStatus = 'PENDING' | 'ACCEPTED' | 'REJECTED';

/** One line of the plan: which warehouse supplies which order. Always the whole order. */
export interface Assignment {
    orderId: string;
    storeName: string;
    warehouseId: number;
    warehouseName: string;
    distanceKm: number;
    transportCost: number;
}

/** An order that no warehouse can supply in this run. It stays pending for the next wave. */
export interface UnmetOrder {
    orderId: string;
    storeName: string;
    reason: string;
}

/** One optimization run over a wave of orders, waiting for a manager decision. */
export interface OptimizationRun {
    id: string;
    strategy: 'BASELINE' | 'MILP';
    baselineCost: number;
    optimizedCost: number;
    status: RunStatus;
    decidedAt: string | null;
    rejectionReason: string | null;
    assignments: Assignment[];
    unmetOrders: UnmetOrder[];
}