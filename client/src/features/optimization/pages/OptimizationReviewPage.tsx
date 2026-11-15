import { useEffect, useState } from 'react';
import PageHeader from '../../../components/ui/PageHeader';
import StatCard from '../../../components/ui/StatCard';
import Card from '../../../components/ui/Card';
import Badge from '../../../components/ui/Badge';
import AssignmentTable from '../components/AssignmentTable';
import UnmetOrderTable from '../components/UnmetOrderTable';
import { getLatestOptimizationRun } from '../api/optimizationApi';
import {
    RUN_STATUS,
    formatEuro,
    getOrderCount,
    getSavings,
    getSavingsPercent,
    getWarehouseCount,
} from '../plan';
import type { OptimizationRun } from '../types';
import styles from './OptimizationReviewPage.module.css';

export default function OptimizationReviewPage() {
    const [run, setRun] = useState<OptimizationRun | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let ignore = false;

        getLatestOptimizationRun()
            .then((data) => {
                if (!ignore) setRun(data);
            })
            .catch((caught) => {
                console.error('Loading the optimization run failed:', caught);
                if (!ignore) setError('The optimization plan could not be loaded.');
            });

        return () => {
            ignore = true;
        };
    }, []);

    const status = run ? RUN_STATUS[run.status] : null;
    const unmetCount = run?.unmetOrders.length ?? 0;

    return (
        <>
            <PageHeader
                title="Optimization & Recommendations"
                subtitle="Review the recommended warehouse for each order before taking a decision."
            />

            {error && (
                <p className={styles.error} role="alert">
                    {error}
                </p>
            )}

            <section
                className={styles.stats}
                aria-label="Optimization figures"
                aria-busy={!run && !error}
            >
                <StatCard
                    label="Orders in Wave"
                    value={run ? getOrderCount(run) : '—'}
                    hint={run ? `Run ${run.id}` : undefined}
                />
                <StatCard
                    label="Warehouses Used"
                    value={run ? getWarehouseCount(run) : '—'}
                    hint="Supplying at least one order"
                />
                <StatCard
                    label="Cost Savings"
                    value={run ? `${getSavingsPercent(run).toFixed(1)}%` : '—'}
                    hint={
                        run
                            ? `${formatEuro(run.baselineCost)} baseline → ${formatEuro(run.optimizedCost)} optimized`
                            : undefined
                    }
                />
                <StatCard
                    label="Not Fulfillable"
                    value={run ? unmetCount : '—'}
                    hint={
                        run
                            ? unmetCount > 0
                                ? 'Stay pending for the next wave'
                                : 'Every order can be supplied'
                            : undefined
                    }
                />
            </section>

            <Card
                title="Recommendations"
                subtitle={
                    run
                        ? `${run.strategy} strategy · ${formatEuro(getSavings(run))} cheaper than the baseline`
                        : 'Recommended warehouse per order'
                }
                actions={status && <Badge variant={status.variant}>{status.label}</Badge>}
                footer={<span>Sample data</span>}
            >
                {!error && !run && <p className={styles.message}>Loading…</p>}

                {run && run.assignments.length === 0 && (
                    <p className={styles.message}>No order in this wave can be supplied.</p>
                )}

                {run && run.assignments.length > 0 && <AssignmentTable assignments={run.assignments} />}
            </Card>

            {run && unmetCount > 0 && (
                <div className={styles.section}>
                    <Card
                        title="Not Fulfillable"
                        subtitle="These orders are not part of the plan and stay pending for the next wave."
                    >
                        <UnmetOrderTable unmetOrders={run.unmetOrders} />
                    </Card>
                </div>
            )}

            {run?.status === 'PENDING' && (
                <div className={styles.review} role="note">
                    <strong>Review required</strong>
                    <span>A manager must accept the plan before the warehouse assignments become active.</span>
                </div>
            )}
        </>
    );
}