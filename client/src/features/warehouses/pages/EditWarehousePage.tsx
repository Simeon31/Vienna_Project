import { useNavigate, useParams } from 'react-router';
import Breadcrumb from '../../../components/ui/Breadcrumb';
import PageHeader from '../../../components/ui/PageHeader';
import ButtonLink from '../../../components/ui/ButtonLink';
import WarehouseForm from '../components/WarehouseForm';
import { updateWarehouse } from '../api/warehousesApi';
import { useWarehouse } from '../hooks/useWarehouse';
import type { Warehouse, WarehouseFormValues, WarehouseInput } from '../types';
import styles from './EditWarehousePage.module.css';

function toFormValues(warehouse: Warehouse): WarehouseFormValues {
    return {
        name: warehouse.name,
        address: warehouse.address,
        latitude: String(warehouse.latitude),
        longitude: String(warehouse.longitude),
        capacity: String(warehouse.capacity),
    };
}

export default function EditWarehousePage() {
    const { id } = useParams();
    const { warehouseId, warehouse, notFound, failed } = useWarehouse(id);
    const navigate = useNavigate();

    const handleUpdate = async (input: WarehouseInput) => {
        const updated = await updateWarehouse(warehouseId, input);
        navigate('/warehouses', { state: { notice: `${updated.name} was updated.` } });
    };

    return (
        <>
            <Breadcrumb items={[{ label: 'Warehouses', to: '/warehouses' }, { label: 'Edit Warehouse' }]} />
            <PageHeader
                title="Edit Warehouse"
                subtitle="Update an existing warehouse's information."
                actions={
                    <ButtonLink to="/warehouses">
                        <span aria-hidden="true">←</span> Back to Warehouses
                    </ButtonLink>
                }
            />

            {notFound && (
                <p className={styles.message} role="alert">
                    This warehouse does not exist.
                </p>
            )}

            {failed && (
                <p className={styles.message} role="alert">
                    The warehouse could not be loaded. Please try again later.
                </p>
            )}

            {!notFound && !failed && !warehouse && <p className={styles.message}>Loading…</p>}

            {warehouse && (
                <WarehouseForm
                    key={warehouse.id}
                    initialValues={toFormValues(warehouse)}
                    submitLabel="Save Changes"
                    status={{
                        title: 'Current Warehouse Status',
                        label: warehouse.active ? 'Active' : 'Inactive',
                        hint: 'Read-only',
                        note: 'Warehouse activation and deactivation are managed from the Warehouses overview.',
                    }}
                    footerNote="Updated warehouse details will be saved."
                    onSubmit={handleUpdate}
                    onCancel={() => navigate('/warehouses')}
                />
            )}
        </>
    );
}