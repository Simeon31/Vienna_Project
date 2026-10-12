import { useEffect, useState } from 'react';
import Card from '../../../components/ui/Card';
import Badge from '../../../components/ui/Badge';
import ButtonLink from '../../../components/ui/ButtonLink';
import { WARNING_THRESHOLD, getCapacityStatus, getCommittedPercent } from '../../warehouses/capacity';
import { getWarehouseCapacities } from '../api/dashboardApi';
import type { WarehouseCapacity } from '../types';
import styles from './CapacityAlertsPanel.module.css';

export default function CapacityAlertsPanel() {
    const [alerts, setAlerts] = useState<WarehouseCapacity[] | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        let ignore = false;

        getWarehouseCapacities()
            .then((data) => {
                if (!ignore) {
                    setAlerts(
                        data.filter(
                            (item) => getCommittedPercent(item) >= WARNING_THRESHOLD,
                        ),
                    );
                }
            })
            .catch(() => {
                if (!ignore) setError('Capacity alerts could not be loaded.');
            });

        return () => {
            ignore = true;
        };
    }, []);

    return (
        <Card
            title="Capacity Alerts"
            subtitle={`Locations at or above the ${WARNING_THRESHOLD}% warning threshold`}
        >
            {error && (
                <p className={styles.message} role="alert">
                    {error}
                </p>
            )}

            {!error && !alerts && <p className={styles.message}>Loading…</p>}

            {alerts && alerts.length === 0 && (
                <p className={styles.message}>All warehouses are below the warning threshold.</p>
            )}

            {alerts && alerts.length > 0 && (
                <ul className={styles.list}>
                    {alerts.map((item) => {
                        const committed = Math.round(getCommittedPercent(item));
                        const status = getCapacityStatus(committed);

                        return (
                            <li key={item.id} className={`${styles.alert} ${styles[status.variant]}`}>
                                <div className={styles.text}>
                                    <p className={styles.title}>
                                        <span className={styles.name}>{item.name}</span>
                                        <Badge variant={status.variant}>
                                            {status.label} · {committed}%
                                        </Badge>
                                    </p>
                                    <p className={styles.detail}>
                                        {item.occupiedPercent}% occupied with another {item.reservedPercent}% reserved
                                        for incoming inventory.
                                    </p>
                                </div>
                                <ButtonLink to="/warehouses" aria-label={`View Warehouse ${item.name}`}>
                                    View Warehouse
                                </ButtonLink>
                            </li>
                        );
                    })}
                </ul>
            )}
        </Card>
    );
}