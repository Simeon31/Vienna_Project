import Modal from '../../../components/ui/Modal';
import Button from '../../../components/ui/Button';
import DetailList from '../../../components/ui/DetailList';
import type { Warehouse } from '../types';

interface CannotDeactivateDialogProps {
    /** The warehouse that could not be deactivated, or null when the dialog is closed. */
    warehouse: Warehouse | null;
    onClose: () => void;
}

export default function CannotDeactivateDialog({ warehouse, onClose }: CannotDeactivateDialogProps) {
    return (
        <Modal
            open={warehouse !== null}
            onClose={onClose}
            title="Cannot Deactivate Warehouse"
            description="This warehouse cannot be deactivated because it still has active shipments. Complete or reassign all active work before trying again."
            actions={
                <Button variant="secondary" onClick={onClose}>
                    Back to Warehouses
                </Button>
            }
        >
            {warehouse && (
                <DetailList
                    items={[
                        { label: 'Warehouse', value: warehouse.name },
                        { label: 'Current Status', value: 'Active' },
                        { label: 'Deactivation', value: 'Blocked' },
                    ]}
                />
            )}
        </Modal>
    );
}