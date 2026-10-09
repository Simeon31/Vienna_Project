import styles from './StatCard.module.css';

interface StatCardProps {
    label: string;
    value: number | string;
    hint?: string;
}

export default function StatCard({ label, value, hint }: StatCardProps) {
    const displayValue = typeof value === 'number' ? value.toLocaleString('en-US') : value;

    return (
        <div className={styles.card}>
            <p className={styles.label}>{label}</p>
            <p className={styles.value}>{displayValue}</p>
            {hint && <p className={styles.hint}>{hint}</p>}
        </div>
    );
}