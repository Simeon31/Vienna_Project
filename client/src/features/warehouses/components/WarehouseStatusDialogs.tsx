import WarehouseStatusDialog from './WarehouseStatusDialog';
import CannotDeactivateDialog from './CannotDeactivateDialog';
import type { WarehouseStatusChange } from '../hooks/useWarehouseStatusChange';

interface WarehouseStatusDialogsProps {
    /** The object returned by useWarehouseStatusChange. */
    statusChange: WarehouseStatusChange;
}

/** Renders the confirmation and the "cannot deactivate" dialog for a status change. */
export default function WarehouseStatusDialogs({ statusChange }: WarehouseStatusDialogsProps) {
    return (
        <>
            <WarehouseStatusDialog
                pendingChange={statusChange.pendingChange}
                saving={statusChange.saving}
                error={statusChange.error}
                onCancel={statusChange.cancel}
                onConfirm={statusChange.confirm}
            />
            <CannotDeactivateDialog
                warehouse={statusChange.blockedWarehouse}
                onClose={statusChange.closeBlocked}
            />
        </>
    );
}