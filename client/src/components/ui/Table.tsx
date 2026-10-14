import type { TableHTMLAttributes } from 'react';
import styles from './Table.module.css';

/**
 * Table with the shared look. Scrolls horizontally inside its container on small screens.
 * Put <thead> and <tbody> inside, like a normal table.
 */
export default function Table({ className, ...rest }: TableHTMLAttributes<HTMLTableElement>) {
    const classes = [styles.table, className].filter(Boolean).join(' ');

    return (
        <div className={styles.wrap}>
            <table className={classes} {...rest} />
        </div>
    );
}