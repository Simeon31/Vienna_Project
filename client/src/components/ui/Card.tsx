import { useId, type ReactNode } from 'react';
import styles from './Card.module.css';

interface CardProps {
    title: string;
    subtitle?: string;
    children: ReactNode;
    footer?: ReactNode;
}

export default function Card({ title, subtitle, children, footer }: CardProps) {
    const headingId = useId();

    return (
        <section className={styles.card} aria-labelledby={headingId}>
            <header className={styles.header}>
                <h2 id={headingId} className={styles.title}>
                    {title}
                </h2>
                {subtitle && <p className={styles.subtitle}>{subtitle}</p>}
            </header>

            <div className={styles.body}>{children}</div>

            {footer && <footer className={styles.footer}>{footer}</footer>}
        </section>
    );
}