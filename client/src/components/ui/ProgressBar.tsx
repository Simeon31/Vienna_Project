import styles from './ProgressBar.module.css';

interface ProgressBarProps {
    label: string;
    primary: number;
    secondary?: number;
}

function clampPercent(value: number): number {
    return Math.min(Math.max(value, 0), 100);
}

export default function ProgressBar({ label, primary, secondary = 0 }: ProgressBarProps) {
    const primaryWidth = clampPercent(primary);
    const secondaryWidth = Math.min(clampPercent(secondary), 100 - primaryWidth);
    const total = Math.round(primaryWidth + secondaryWidth);

    return (
        <div
            className={styles.track}
            role="progressbar"
            aria-label={label}
            aria-valuemin={0}
            aria-valuemax={100}
            aria-valuenow={total}>

            <div className={styles.primary} style={{ width: `${primaryWidth}%` }} />
            <div className={styles.secondary} style={{ width: `${secondaryWidth}%` }} />
        </div>
    );
}