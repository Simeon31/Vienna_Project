import { useEffect, useState } from 'react';
import PageHeader from '../../../components/ui/PageHeader';
import StatCard from '../../../components/ui/StatCard';
import { getDashboardStats } from '../api/dashboardApi';
import type { DashboardStats } from '../types';
import styles from './DashboardPage.module.css';

export default function DashboardPage() {
    const [stats, setStats] = useState<DashboardStats | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let ignore = false;

        getDashboardStats()
            .then((data) => {
                if (!ignore) setStats(data);
            })
            .catch(() => {
                if (!ignore) setError('Dashboard data could not be loaded.');
            });

        return () => {
            ignore = true;
        };
    }, []);

    const activePercent =
        stats && stats.totalAccounts > 0
            ? Math.round((stats.activeAccounts / stats.totalAccounts) * 100)
            : 0;

    return (
        <>
            <PageHeader
                title="Dashboard Overview"
                subtitle="Administration and warehouse monitoring"
            />

            {error && (
                <p className={styles.error} role="alert">
                    {error}
                </p>
            )}

            <section className={styles.stats} aria-label="Key figures" aria-busy={!stats && !error}>
                <StatCard
                    label="Total Accounts"
                    value={stats?.totalAccounts ?? '—'}
                    hint="All registered users"
                />
                <StatCard
                    label="Active Accounts"
                    value={stats?.activeAccounts ?? '—'}
                    hint={stats ? `${activePercent}% of all accounts` : undefined}
                />
                <StatCard
                    label="Total Warehouses"
                    value={stats?.totalWarehouses ?? '—'}
                    hint={stats ? `${stats.activeWarehouses} active` : undefined}
                />
                <StatCard
                    label="Warehouses Near Capacity"
                    value={stats?.warehousesNearCapacity ?? '—'}
                    hint="At or above 75% committed"
                />
            </section>
        </>
    );
}