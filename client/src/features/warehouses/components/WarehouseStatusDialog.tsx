import Modal from '../../../components/ui/Modal';
import Button from '../../../components/ui/Button';
import DetailList from '../../../components/ui/DetailList';
import type { PendingStatusChange } from '../types';
import styles from './WarehouseDialogs.module.css';

interface WarehouseStatusDialogProps {
    /** The planned change, or null when the dialog is closed. */
    pendingChange: PendingStatusChange | null;
    /** True while the request is running; disables the buttons. */
    saving: boolean;
    /** Error message if the last attempt failed. */
    error: string | null;
    onCancel: () => void;
    onConfirm: () => void;
}

export default function WarehouseStatusDialog({
                                                  pendingChange,
                                                  saving,
                                                  error,
                                                  onCancel,
                                                  onConfirm,
                                              }: WarehouseStatusDialogProps) {
    const warehouse = pendingChange?.warehouse;
    const activating = pendingChange?.action === 'activate';

    return (
        <Modal
            open={pendingChange !== null}
            onClose={onCancel}
            title={activating ? 'Activate Warehouse?' : 'Deactivate Warehouse?'}
            description={
                warehouse &&
                (activating ? (
                    <>
                        Are you sure you want to activate <strong>{warehouse.name}</strong>? The warehouse
                        will become available for new warehouse operations.
                    </>
                ) : (
                    <>
                        Are you sure you want to deactivate <strong>{warehouse.name}</strong>? An inactive
                        warehouse will not be available for new shipment or order assignments.
                    </>
                ))
            }
            actions={
                <>
                    <Button variant="secondary" onClick={onCancel} disabled={saving}>
                        Cancel
                    </Button>
                    <Button variant={activating ? 'primary' : 'danger'} onClick={onConfirm} disabled={saving}>
                        {saving ? 'Saving…' : activating ? 'Activate Warehouse' : 'Deactivate Warehouse'}
                    </Button>
                </>
            }
        >
            {warehouse && (
                <DetailList
                    items={[
                        { label: 'Warehouse', value: warehouse.name },
                        { label: 'Location', value: warehouse.address },
                        { label: 'Current Status', value: warehouse.active ? 'Active' : 'Inactive' },
                        { label: 'New Status', value: activating ? 'Active' : 'Inactive' },
                    ]}
                />
            )}

            {!activating && (
                <p className={styles.note}>
                    Before deactivating, the system checks whether this warehouse has active shipments.
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