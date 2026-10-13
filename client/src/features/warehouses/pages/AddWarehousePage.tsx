import Breadcrumb from '../../../components/ui/Breadcrumb';
import PageHeader from '../../../components/ui/PageHeader';
import ButtonLink from '../../../components/ui/ButtonLink';

export default function AddWarehousePage() {
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
            <p>The form follows in the next step.</p>
        </>
    );
}