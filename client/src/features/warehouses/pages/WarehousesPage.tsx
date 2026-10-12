import { useEffect, useState } from 'react';
import PageHeader from '../../../components/ui/PageHeader';
import StatCard from '../../../components/ui/StatCard';
import Card from '../../../components/ui/Card';
import ButtonLink from '../../../components/ui/ButtonLink';
import WarehouseTable from '../components/WarehouseTable';
import { getWarehouses } from '../api/warehousesApi';
import { CRITICAL_THRESHOLD, WARNING_THRESHOLD, isNearCapacity } from '../capacity';
import type { Warehouse } from '../types';
import styles from './WarehousesPage.module.css';

export default function WarehousesPage() {
    const [warehouses, setWarehouses] = useState<Warehouse[] | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let ignore = false;

        getWarehouses()
            .then((data) => {
                if (!ignore) setWarehouses(data);
            })
            .catch(() => {
                if (!ignore) setError('Warehouses could not be loaded.');
            });

        return () => {
            ignore = true;
        };
    }, []);

    // Derived from the list on every render, so the figures always match the table
    const total = warehouses?.length ?? 0;
    const active = warehouses?.filter((warehouse) => warehouse.active).length ?? 0;
    const nearCapacity = warehouses?.filter(isNearCapacity).length ?? 0;
    const activeShare = total > 0 ? ((active / total) * 100).toFixed(1) : '0.0';

    return (
        <>
            <PageHeader
                title="Warehouse Management"
                subtitle="View and manage warehouses, monitor capacity and update warehouse information."
                actions={
                    <ButtonLink to="/warehouses/new" variant="primary">
                        + Add Warehouse
                    </ButtonLink>
                }
            />

            {error && (
                <p className={styles.error} role="alert">
                    {error}
                </p>
            )}

            <section
                className={styles.stats}
                aria-label="Warehouse figures"
                aria-busy={!warehouses && !error}
            >
                <StatCard
                    label="Total Warehouses"
                    value={warehouses ? total : '—'}
                    hint={warehouses ? `${total - active} inactive` : undefined}
                />
                <StatCard
                    label="Active Warehouses"
                    value={warehouses ? active : '—'}
                    hint={warehouses ? `${activeShare}% of all warehouses` : undefined}
                />
                <StatCard
                    label="Warehouses Near Capacity"
                    value={warehouses ? nearCapacity : '—'}
                    hint={`At or above ${WARNING_THRESHOLD}% committed`}
                />
            </section>

            <Card
                title="Warehouses"
                subtitle="All registered warehouses"
                footer={
                    <>
            <span>
              Committed = occupied + reserved incoming · Normal below {WARNING_THRESHOLD}% ·
              Warning {WARNING_THRESHOLD}–{CRITICAL_THRESHOLD - 1}% · Critical{' '}
                {CRITICAL_THRESHOLD}% or above
            </span><span>Sample data</span></>
            }>
                {!error && !warehouses && <p className={styles.message}>Loading…</p>}

                {warehouses && warehouses.length === 0 && (
                    <p className={styles.message}>No warehouses yet. Add the first one.</p>
                )}

                {warehouses && warehouses.length > 0 && <WarehouseTable warehouses={warehouses} />}
            </Card>
        </>
    );
}