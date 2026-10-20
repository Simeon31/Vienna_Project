import { useState } from 'react';
import { useParams } from 'react-router';
import Breadcrumb from '../../../components/ui/Breadcrumb';
import PageHeader from '../../../components/ui/PageHeader';
import Card from '../../../components/ui/Card';
import Badge from '../../../components/ui/Badge';
import Button from '../../../components/ui/Button';
import ButtonLink from '../../../components/ui/ButtonLink';
import WarehouseStatusDialogs from '../components/WarehouseStatusDialogs';
import { useWarehouse } from '../hooks/useWarehouse';
import { useWarehouseStatusChange } from '../hooks/useWarehouseStatusChange';
import styles from './WarehouseDetailPage.module.css';
import CapacityOverview from '../components/CapacityOverview';
import NearCapacityAlert from '../components/NearCapacityAlert';

export default function WarehouseDetailPage() {
    const { id } = useParams();
    const { warehouse, setWarehouse, notFound, failed } = useWarehouse(id);
    const [notice, setNotice] = useState<string | null>(null);

    const statusChange = useWarehouseStatusChange((updated, action) => {
        setWarehouse(updated);
        setNotice(`${updated.name} was ${action === 'activate' ? 'activated' : 'deactivated'}.`);
    });

    const loading = !warehouse && !notFound && !failed;

    return (
        <>
            <Breadcrumb
                items={[{ label: 'Warehouses', to: '/warehouses' }, { label: 'Warehouse Details' }]}
            />
            <PageHeader
                title={warehouse?.name ?? 'Warehouse Details'}
                subtitle="Warehouse information, capacity and operational status."
                actions={
                    <>
                        <ButtonLink to="/warehouses">
                            <span aria-hidden="true">←</span> Back to Warehouses
                        </ButtonLink>
                        {warehouse && (
                            <ButtonLink to={`/warehouses/${warehouse.id}/edit`} variant="primary">
                                Edit Warehouse
                            </ButtonLink>
                        )}
                    </>
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
            {loading && <p className={styles.message}>Loading…</p>}

            {notice && (
                <p className={styles.notice} role="status">
                    {notice}
                </p>
            )}

            {warehouse && (
                <div className={styles.sections}>
                    <Card title="Warehouse Information">
                        <dl className={styles.facts}>
                            <div>
                                <dt className={styles.factLabel}>Warehouse Name</dt>
                                <dd className={styles.factValue}>{warehouse.name}</dd>
                            </div>
                            <div>
                                <dt className={styles.factLabel}>Location</dt>
                                <dd className={styles.factValue}>{warehouse.address}</dd>
                            </div>
                            <div>
                                <dt className={styles.factLabel}>Coordinates</dt>
                                <dd className={styles.factValue}>
                                    {warehouse.latitude}, {warehouse.longitude}
                                </dd>
                            </div>
                            <div>
                                <dt className={styles.factLabel}>Total Capacity</dt>
                                <dd className={styles.factValue}>
                                    {warehouse.capacity.toLocaleString('en-US')} m³
                                </dd>
                            </div>
                            <div>
                                <dt className={styles.factLabel}>Status</dt>
                                <dd className={styles.factValue}>
                                    <Badge variant={warehouse.active ? 'success' : 'neutral'}>
                                        {warehouse.active ? 'Active' : 'Inactive'}
                                    </Badge>
                                </dd>
                            </div>
                        </dl>
                    </Card>

                    <CapacityOverview warehouse={warehouse} />
                    <NearCapacityAlert warehouse={warehouse} />

                    <Card title="Warehouse Status and Actions">
                        <div className={styles.statusRow}>
                            <p className={styles.currentStatus}>
                                Current Status:
                                <Badge variant={warehouse.active ? 'success' : 'neutral'}>
                                    {warehouse.active ? 'Active' : 'Inactive'}
                                </Badge>
                            </p>
                            <div className={styles.statusActions}>
                                <ButtonLink to={`/warehouses/${warehouse.id}/edit`}>Edit Warehouse</ButtonLink>
                                <Button
                                    variant="secondary"
                                    onClick={() => {
                                        setNotice(null);
                                        statusChange.open(warehouse);
                                    }}
                                >
                                    {warehouse.active ? 'Deactivate Warehouse' : 'Activate Warehouse'}
                                </Button>
                            </div>
                        </div>
                        <p className={styles.hint}>
                            Before deactivation, the system checks whether this warehouse has active shipments.
                        </p>
                    </Card>
                </div>
            )}

            <WarehouseStatusDialogs statusChange={statusChange} />
        </>
    );
}