import Modal from '../../../components/ui/Modal';
import Button from '../../../components/ui/Button';
import DetailList from '../../../components/ui/DetailList';
import { formatEuro, getSavings } from '../plan';
import type { OptimizationRun } from '../types';
import styles from './PlanDialogs.module.css';

interface AcceptPlanDialogProps {
    /** The run to accept; its figures are shown as a summary. */
    run: OptimizationRun;
    /** Whether the dialog is shown. */
    open: boolean;
    /** True while the request is running; disables the buttons. */
    saving: boolean;
    /** Error message if the last attempt failed. */
    error: string | null;
    onCancel: () => void;
    onConfirm: () => void;
}

export default function AcceptPlanDialog({ run, open, saving, error, onCancel, onConfirm }: AcceptPlanDialogProps) {
    const assigned = run.assignments.length;
    const unmet = run.unmetOrders.length;

    return (
        <Modal
            open={open}
            onClose={onCancel}
            title={`Accept plan for ${assigned} ${assigned === 1 ? 'order' : 'orders'}?`}
            description="The warehouse assignments become active and are sent to the warehouses."
            actions={
                <>
                    <Button variant="secondary" onClick={onCancel} disabled={saving}>
                        Go Back
                    </Button>
                    <Button variant="primary" onClick={onConfirm} disabled={saving}>
                        {saving ? 'Accepting…' : 'Accept & Assign'}
                    </Button>
                </>
            }
        >
            <DetailList
                items={[
                    { label: 'Optimization Run', value: run.id },
                    { label: 'Orders Assigned', value: assigned },
                    { label: 'Not Fulfillable', value: unmet },
                    { label: 'Transport Cost', value: formatEuro(run.optimizedCost) },
                    { label: 'Savings vs. Baseline', value: formatEuro(getSavings(run)) },
                ]}
            />

            {unmet > 0 && (
                <p className={styles.note}>
                    <strong>
                        {unmet} {unmet === 1 ? 'order' : 'orders'}
                    </strong>{' '}
                    cannot be fulfilled and will stay pending for the next wave.
                </p>
            )}

            {error && (
                <p className={styles.error} role="alert">
                    {error}
                </p>
            )}
        </Modal>
    );
}