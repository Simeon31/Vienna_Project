import { useEffect, useState } from 'react';
import { Link } from 'react-router';
import Card from '../../../components/ui/Card';
import ProgressBar from '../../../components/ui/ProgressBar';
import Badge from '../../../components/ui/Badge';
import { getCapacityStatus } from '../../warehouses/capacity';
import { getWarehouseCapacities } from '../api/dashboardApi';
import type { WarehouseCapacity } from '../types';
import styles from './WarehouseCapacityPanel.module.css';

export default function WarehouseCapacityPanel() {
    const [capacities, setCapacities] = useState<WarehouseCapacity[] | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let ignore = false;

        getWarehouseCapacities()
            .then((data) => {
                if (!ignore) setCapacities(data);
            })
            .catch(() => {
                if (!ignore) setError('Warehouse capacity could not be loaded.');
            });

        return () => {
            ignore = true;
        };
    }, []);

    return (
        <Card
            title="Warehouse Capacity"
            subtitle="Committed capacity across monitored locations"
            footer={
                <>
                    <span>Sample utilization data until inventory is connected</span>
                    <Link to="/warehouses" className={styles.link}>
                        View All Warehouses <span aria-hidden="true">→</span>
                    </Link>
                </>
            }
        >
            {error && (
                <p className={styles.message} role="alert">
                    {error}
                </p>
            )}

            {!error && !capacities && <p className={styles.message}>Loading…</p>}

            {capacities && capacities.length === 0 && (
                <p className={styles.message}>No active warehouses yet.</p>
            )}

            {capacities && capacities.length > 0 && (
                <ul className={styles.list}>
                    {capacities.map((item) => {
                        const committed = item.occupiedPercent + item.reservedPercent;
                        const status = getCapacityStatus(committed);

                        return (
                            <li key={item.id} className={styles.row}>
                                <div className={styles.info}>
                                    <p className={styles.name} title={item.name}>
                                        {item.name}
                                    </p>
                                    <p className={styles.location} title={item.location}>
                                        {item.location}
                                    </p>
                                </div>
                                <div className={styles.bar}>
                                    <ProgressBar
                                        label={`${item.name} committed capacity`}
                                        primary={item.occupiedPercent}
                                        secondary={item.reservedPercent}
                                    />
                                </div>
                                <span className={styles.percent}>{Math.round(committed)}%</span>
                                <span className={styles.badge}>
                  <Badge variant={status.variant}>{status.label}</Badge>
                </span>
                            </li>
                        );
                    })}
                </ul>
            )}
        </Card>
    );
}