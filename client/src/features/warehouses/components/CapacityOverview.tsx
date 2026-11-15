import Card from '../../../components/ui/Card';
import Badge from '../../../components/ui/Badge';
import ProgressBar from '../../../components/ui/ProgressBar';
import {
    CRITICAL_THRESHOLD,
    WARNING_THRESHOLD,
    getCapacityStatus,
    getCommittedPercent,
    getSampleUtilization,
} from '../capacity';
import type { Warehouse } from '../types';
import styles from './CapacityOverview.module.css';

interface CapacityOverviewProps {
    /** The warehouse whose capacity is shown. */
    warehouse: Warehouse;
}

function formatVolume(value: number): string {
    return `${Math.round(value).toLocaleString('en-US')} m³`;
}

export default function CapacityOverview({ warehouse }: CapacityOverviewProps) {
    const utilization = getSampleUtilization(warehouse.id);
    const committedPercent = Math.round(getCommittedPercent(utilization));
    const availablePercent = Math.max(0, 100 - committedPercent);
    const status = getCapacityStatus(committedPercent);

    // Percentages turned into cubic meters of this warehouse
    const occupied = (warehouse.capacity * utilization.occupiedPercent) / 100;
    const reserved = (warehouse.capacity * utilization.reservedPercent) / 100;
    const available = Math.max(0, warehouse.capacity - occupied - reserved);

    const figures = [
        { label: 'Total Capacity', value: formatVolume(warehouse.capacity) },
        {
            label: 'Physically Occupied',
            value: formatVolume(occupied),
            share: `${utilization.occupiedPercent}% of total`,
        },
        {
            label: 'Reserved Incoming',
            value: formatVolume(reserved),
            share: `${utilization.reservedPercent}% of total`,
        },
        { label: 'Available Capacity', value: formatVolume(available), share: `${availablePercent}% of total` },
        {
            label: 'Committed Capacity',
            value: formatVolume(occupied + reserved),
            share: `${committedPercent}% of total`,
        },
    ];

    const levels = [
        { label: 'Normal', text: `Normal: below ${WARNING_THRESHOLD}%` },
        { label: 'Warning', text: `Warning: ${WARNING_THRESHOLD}–${CRITICAL_THRESHOLD - 1}%` },
        { label: 'Critical', text: `Critical: ${CRITICAL_THRESHOLD}% or above` },
    ];

    return (
        <Card
            title="Capacity Overview"
            actions={
                <>
                    <span className={styles.statusLabel}>Capacity Status:</span>
                    <Badge variant={status.variant}>{status.label}</Badge>
                </>
            }
            footer={<span>Sample utilization data until inventory is connected</span>}
        >
            <dl className={styles.figures}>
                {figures.map((figure) => (
                    <div key={figure.label}>
                        <dt className={styles.figureLabel}>{figure.label}</dt>
                        <dd className={styles.figureValue}>{figure.value}</dd>
                        {figure.share && <dd className={styles.figureShare}>{figure.share}</dd>}
                    </div>
                ))}
            </dl>

            <div className={styles.bar}>
                <ProgressBar
                    label={`${warehouse.name} committed capacity`}
                    primary={utilization.occupiedPercent}
                    secondary={utilization.reservedPercent}
                />
            </div>

            <ul className={styles.legend}>
                <li>
                    <span className={`${styles.swatch} ${styles.occupied}`} aria-hidden="true" />
                    Occupied · {utilization.occupiedPercent}%
                </li>
                <li>
                    <span className={`${styles.swatch} ${styles.reserved}`} aria-hidden="true" />
                    Reserved Incoming · {utilization.reservedPercent}%
                </li>
                <li>
                    <span className={`${styles.swatch} ${styles.available}`} aria-hidden="true" />
                    Available · {availablePercent}%
                </li>
            </ul>

            <p className={styles.note}>
                Committed capacity includes occupied space and capacity reserved for incoming shipments.
            </p>

            <ul className={styles.levels} aria-label="Capacity thresholds">
                {levels.map((level) => {
                    const isCurrent = level.label === status.label;
                    return (
                        <li key={level.label} className={isCurrent ? styles.currentLevel : undefined}>
                            {level.text}
                            {isCurrent && <span className="visually-hidden"> (current)</span>}
                        </li>
                    );
                })}
            </ul>
        </Card>
    );
}