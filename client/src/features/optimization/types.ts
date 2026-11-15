/** Decision state of an optimization run. */
export type RunStatus = 'PENDING' | 'ACCEPTED' | 'REJECTED';

/** Whether the optimizer found a warehouse that can fully supply the order. */
export type AssignmentResult = 'RECOMMENDED' | 'INSUFFICIENT';

/** One line of the plan: which warehouse supplies which order. */
export interface Assignment {
    orderId: string;
    warehouseId: number;
    warehouseName: string;
    coveragePercent: number;
    cost: number;
    result: AssignmentResult;
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
}