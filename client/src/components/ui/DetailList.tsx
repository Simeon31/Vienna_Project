import type { ReactNode } from 'react';
import styles from './DetailList.module.css';

export interface DetailItem {
    /** Name of the value, e.g. "Location". */
    label: string;
    /** The value itself. Can contain elements, e.g. a Badge. */
    value: ReactNode;
}

interface DetailListProps {
    /** Rows from top to bottom. */
    items: DetailItem[];
}

/** List of label/value rows, e.g. warehouse details in a dialog or on a detail page. */
export default function DetailList({ items }: DetailListProps) {
    return (
        <dl className={styles.list}>
            {items.map((item) => (
                <div key={item.label} className={styles.row}>
                    <dt className={styles.label}>{item.label}</dt>
                    <dd className={styles.value}>{item.value}</dd>
                </div>
            ))}
        </dl>
    );
}