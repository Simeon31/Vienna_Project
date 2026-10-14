import { useEffect, useState } from 'react';
import Card from '../../../components/ui/Card';
import DonutChart, { type DonutSegment } from '../../../components/ui/DonutChart';
import { getAccountDistribution } from '../api/dashboardApi';
import type { AccountDistribution } from '../types';
import styles from './AccountDistributionPanel.module.css';

export default function AccountDistributionPanel() {
    const [distribution, setDistribution] = useState<AccountDistribution | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let ignore = false;

        getAccountDistribution()
            .then((data) => {
                if (!ignore) setDistribution(data);
            })
            .catch(() => {
                if (!ignore) setError('Account distribution could not be loaded.');
            });

        return () => {
            ignore = true;
        };
    }, []);

    const total = distribution ? distribution.managers + distribution.employees : 0;
    const share = (value: number) => (total > 0 ? ((value / total) * 100).toFixed(1) : '0.0');

    const segments: DonutSegment[] = distribution
        ? [
            { label: 'Managers', value: distribution.managers, color: 'var(--color-chart-1)' },
            { label: 'Employees', value: distribution.employees, color: 'var(--color-chart-2)' },
        ]
        : [];

    return (
        <Card
            title="Account Distribution"
            subtitle="All registered Manager and Employee accounts"
            footer={<span>Sample data until accounts are connected</span>}
        >
            {error && (
                <p className={styles.message} role="alert">
                    {error}
                </p>
            )}

            {!error && !distribution && <p className={styles.message}>Loading…</p>}

            {distribution && (
                <div className={styles.content}>
                    <DonutChart segments={segments} centerLabel="Total" />

                    <ul className={styles.legend}>
                        {segments.map((segment) => (
                            <li key={segment.label} className={styles.item}>
                <span
                    className={styles.swatch}
                    style={{ background: segment.color }}
                    aria-hidden="true"
                />
                                <span className={styles.text}>
                  <span className={styles.label}>{segment.label}</span>
                  <span className={styles.share}>{share(segment.value)}% of accounts</span>
                </span>
                                <span className={styles.value}>{segment.value.toLocaleString('en-US')}</span>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </Card>
    );
}