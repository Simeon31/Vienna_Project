import type { ReactNode } from 'react';
import styles from './Badge.module.css';

export type BadgeVariant = 'danger' | 'warning' | 'success' | 'neutral';

interface BadgeProps {
    variant?: BadgeVariant;
    children: ReactNode;
}

export default function Badge({ variant = 'neutral', children }: BadgeProps) {
    return <span className={`${styles.badge} ${styles[variant]}`}>{children}</span>;
}