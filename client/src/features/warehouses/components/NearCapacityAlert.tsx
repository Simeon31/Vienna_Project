import Badge from '../../../components/ui/Badge';
import {
    getCapacityStatus,
    getCommittedPercent,
    getSampleUtilization,
    isNearCapacity,
} from '../capacity';
import type { Warehouse } from '../types';
import styles from './NearCapacityAlert.module.css';

interface NearCapacityAlertProps {
    /** Shown only if this warehouse is active and at or above the warning threshold. */
    warehouse: Warehouse;
}

export default function NearCapacityAlert({ warehouse }: NearCapacityAlertProps) {
    if (!isNearCapacity(warehouse)) return null;

    const committed = Math.round(getCommittedPercent(getSampleUtilization(warehouse.id)));
    const status = getCapacityStatus(committed);
    const available = Math.max(0, 100 - committed);

    return (
        <section className={`${styles.alert} ${styles[status.variant]}`} aria-label="Capacity warning">
            <div>
                <p className={styles.title}>
                    Warehouse Near Full Capacity
                    <Badge variant={status.variant}>{status.label}</Badge>
                </p>
                <p className={styles.text}>
                    {warehouse.name} has committed {committed}% of its total capacity. Only {available}% remains
                    available.
                </p>
            </div>
            <span className={styles.meta}>Monitoring information only</span>
        </section>
    );
}