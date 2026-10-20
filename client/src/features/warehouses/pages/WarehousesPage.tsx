import { useEffect, useState } from 'react';
import PageHeader from '../../../components/ui/PageHeader';
import StatCard from '../../../components/ui/StatCard';
import Card from '../../../components/ui/Card';
import ButtonLink from '../../../components/ui/ButtonLink';
import WarehouseTable from '../components/WarehouseTable';
import { getWarehouses } from '../api/warehousesApi';
import {CRITICAL_THRESHOLD, WARNING_THRESHOLD, getCapacityStatus, getCommittedPercent, getSampleUtilization, isNearCapacity,} from '../capacity';
import SearchInput from '../../../components/ui/SearchInput';
import SegmentedControl from '../../../components/ui/SegmentedControl';
import type { Warehouse } from '../types';
import styles from './WarehousesPage.module.css';
import WarehouseStatusDialogs from '../components/WarehouseStatusDialogs';
import { useWarehouseStatusChange } from '../hooks/useWarehouseStatusChange';
import { useLocation } from 'react-router';

type StatusFilter = 'all' | 'active' | 'inactive';
type CapacityFilter = 'all' | 'normal' | 'warning' | 'critical';

const STATUS_OPTIONS: { value: StatusFilter; label: string }[] = [
    { value: 'all', label: 'All' },
    { value: 'active', label: 'Active' },
    { value: 'inactive', label: 'Inactive' },
];

const CAPACITY_OPTIONS: { value: CapacityFilter; label: string }[] = [
    { value: 'all', label: 'All' },
    { value: 'normal', label: 'Normal' },
    { value: 'warning', label: 'Warning' },
    { value: 'critical', label: 'Critical' },
];

export default function WarehousesPage() {
    const [warehouses, setWarehouses] = useState<Warehouse[] | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [search, setSearch] = useState('');
    const [statusFilter, setStatusFilter] = useState<StatusFilter>('all');
    const [capacityFilter, setCapacityFilter] = useState<CapacityFilter>('all');
    const location = useLocation();
    const [notice, setNotice] = useState<string | null>(
        (location.state as { notice?: string } | null)?.notice ?? null,
    );

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

    // Filtered view of the list; the figures above always use the full list
    const query = search.trim().toLowerCase();
    const visibleWarehouses = (warehouses ?? []).filter((warehouse) => {
        if (statusFilter === 'active' && !warehouse.active) return false;
        if (statusFilter === 'inactive' && warehouse.active) return false;

        if (capacityFilter !== 'all') {
            const committed = Math.round(getCommittedPercent(getSampleUtilization(warehouse.id)));
            if (getCapacityStatus(committed).label.toLowerCase() !== capacityFilter) return false;
        }

        if (!query) return true;
        return `${warehouse.name} ${warehouse.address}`.toLowerCase().includes(query);
    });

    const statusChange = useWarehouseStatusChange((updated, action) => {
        // Replace the changed warehouse in the list; figures and table update automatically
        setWarehouses((current) =>
            current ? current.map((item) => (item.id === updated.id ? updated : item)) : current,
        );
        setNotice(`${updated.name} was ${action === 'activate' ? 'activated' : 'deactivated'}.`);
    });

    const handleToggleActive = (warehouse: Warehouse) => {
        setNotice(null);
        statusChange.open(warehouse);
    };

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

            <section className={styles.filters} aria-label="Filter warehouses">
                <div className={styles.search}>
                    <SearchInput
                        label="Search warehouses"
                        value={search}
                        onChange={setSearch}
                        placeholder="Search by warehouse name or location"
                    />
                </div>
                <SegmentedControl
                    label="Status"
                    options={STATUS_OPTIONS}
                    value={statusFilter}
                    onChange={setStatusFilter}
                />
                <SegmentedControl
                    label="Capacity"
                    options={CAPACITY_OPTIONS}
                    value={capacityFilter}
                    onChange={setCapacityFilter}
                />
            </section>

            {notice && (
                <p className={styles.notice} role="status">
                    {notice}
                </p>
            )}

            <Card
                title="Warehouses"
                subtitle={
                    warehouses
                        ? `${visibleWarehouses.length} of ${total} warehouses`
                        : 'All registered warehouses'
                }
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

                {warehouses && warehouses.length > 0 && visibleWarehouses.length === 0 && (
                    <p className={styles.message}>No warehouses match your search or filters.</p>
                )}

                {visibleWarehouses.length > 0 && (
                    <WarehouseTable warehouses={visibleWarehouses} onToggleActive={handleToggleActive} />
                )}
            </Card>

            <WarehouseStatusDialogs statusChange={statusChange} />
        </>
    );
}