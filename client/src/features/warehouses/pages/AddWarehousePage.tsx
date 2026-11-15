import { useNavigate } from 'react-router';
import Breadcrumb from '../../../components/ui/Breadcrumb';
import PageHeader from '../../../components/ui/PageHeader';
import ButtonLink from '../../../components/ui/ButtonLink';
import WarehouseForm from '../components/WarehouseForm';
import { createWarehouse } from '../api/warehousesApi';
import type { WarehouseFormValues, WarehouseInput } from '../types';

const EMPTY_VALUES: WarehouseFormValues = {
    name: '',
    address: '',
    latitude: '',
    longitude: '',
    capacity: '',
};

export default function AddWarehousePage() {
    const navigate = useNavigate();

    const handleCreate = async (input: WarehouseInput) => {
        const created = await createWarehouse(input);
        navigate('/warehouses', { state: { notice: `${created.name} was created.` } });
    };

    return (
        <>
            <Breadcrumb items={[{ label: 'Warehouses', to: '/warehouses' }, { label: 'Add Warehouse' }]} />
            <PageHeader
                title="Add Warehouse"
                subtitle="Register a new warehouse in the distribution network."
                actions={
                    <ButtonLink to="/warehouses">
                        <span aria-hidden="true">←</span> Back to Warehouses
                    </ButtonLink>
                }
            />
            <WarehouseForm
                initialValues={EMPTY_VALUES}
                submitLabel="Add Warehouse"
                status={{
                    title: 'Warehouse Status',
                    label: 'Active',
                    hint: 'Set automatically',
                    note: 'New warehouses are automatically created with Active status.',
                }}
                onSubmit={handleCreate}
                onCancel={() => navigate('/warehouses')}
            />
        </>
    );
}