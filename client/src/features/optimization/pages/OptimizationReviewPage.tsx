import { useEffect, useState } from 'react';
import PageHeader from '../../../components/ui/PageHeader';
import StatCard from '../../../components/ui/StatCard';
import Card from '../../../components/ui/Card';
import Badge from '../../../components/ui/Badge';
import Button from '../../../components/ui/Button';
import AssignmentTable from '../components/AssignmentTable';
import UnmetOrderTable from '../components/UnmetOrderTable';
import AcceptPlanDialog from '../components/AcceptPlanDialog';
import {
    acceptOptimizationRun,
    getLatestOptimizationRun,
    OptimizationRunNotPendingError,
} from '../api/optimizationApi';
import {
    RUN_STATUS,
    canAccept,
    formatDateTime,
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
    const [acceptOpen, setAcceptOpen] = useState(false);
    const [saving, setSaving] = useState(false);
    const [decisionError, setDecisionError] = useState<string | null>(null);

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
    const openAccept = () => {
        setDecisionError(null);
        setAcceptOpen(true);
    };

    const handleAccept = async () => {
        if (!run) return;

        setSaving(true);
        setDecisionError(null);

        try {
            const updated = await acceptOptimizationRun(run.id);
            setRun(updated);
            setAcceptOpen(false);
        } catch (caught) {
            if (caught instanceof OptimizationRunNotPendingError) {
                setDecisionError('This plan has already been decided. Reload the page to see its current status.');
            } else {
                console.error('Accepting the plan failed:', caught);
                setDecisionError('The plan could not be accepted. Please try again.');
            }
        } finally {
            setSaving(false);
        }
    };

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
                <div className={styles.decision}>
                    <div className={styles.review} role="note">
                        <strong>Review required</strong>
                        <span>A manager must accept the plan before the warehouse assignments become active.</span>
                    </div>
                    <div className={styles.actions}>
                        {/* US 6.3: the Reject button goes here (enabled when canReject(run)) */}
                        <Button variant="primary" onClick={openAccept} disabled={!canAccept(run)}>
                            Accept Plan
                        </Button>
                    </div>
                </div>
            )}

            {run && run.status !== 'PENDING' && run.decidedAt && (
                <div className={`${styles.review} ${styles.section}`} role="status">
                    <strong>Plan {RUN_STATUS[run.status].label.toLowerCase()}</strong>
                    <span>Decided on {formatDateTime(run.decidedAt)}.</span>
                </div>
            )}

            {run && (
                <AcceptPlanDialog
                    run={run}
                    open={acceptOpen}
                    saving={saving}
                    error={decisionError}
                    onCancel={() => setAcceptOpen(false)}
                    onConfirm={handleAccept}
                />
            )}
        </>
    );
}