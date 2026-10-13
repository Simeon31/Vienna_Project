import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router';
import Breadcrumb from '../../../components/ui/Breadcrumb';
import PageHeader from '../../../components/ui/PageHeader';
import ButtonLink from '../../../components/ui/ButtonLink';
import WarehouseForm from '../components/WarehouseForm';
import { WarehouseNotFoundError, getWarehouse, updateWarehouse } from '../api/warehousesApi';
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
    const warehouseId = Number(id);
    const invalidId = !Number.isInteger(warehouseId) || warehouseId <= 0;
    const navigate = useNavigate();

    const [warehouse, setWarehouse] = useState<Warehouse | null>(null);
    const [loadError, setLoadError] = useState<'not-found' | 'failed' | null>(null);

    useEffect(() => {
        if (invalidId) return;
        let ignore = false;

        getWarehouse(warehouseId)
            .then((data) => {
                if (!ignore) setWarehouse(data);
            })
            .catch((caught) => {
                if (!ignore) setLoadError(caught instanceof WarehouseNotFoundError ? 'not-found' : 'failed');
            });

        return () => {
            ignore = true;
        };
    }, [warehouseId, invalidId]);

    const handleUpdate = async (input: WarehouseInput) => {
        const updated = await updateWarehouse(warehouseId, input);
        navigate('/warehouses', { state: { notice: `${updated.name} was updated.` } });
    };

    const notFound = invalidId || loadError === 'not-found';

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

            {loadError === 'failed' && (
                <p className={styles.message} role="alert">
                    The warehouse could not be loaded. Please try again later.
                </p>
            )}

            {!notFound && !loadError && !warehouse && <p className={styles.message}>Loading…</p>}

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