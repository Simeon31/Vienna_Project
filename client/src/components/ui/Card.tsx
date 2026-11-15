import { useId, type ReactNode } from 'react';
import styles from './Card.module.css';

interface CardProps {
    title: string;
    subtitle?: string;
    /** Elements on the right of the title, e.g. a Badge or a link. */
    actions?: ReactNode;
    children: ReactNode;
    footer?: ReactNode;
}

export default function Card({ title, subtitle, actions, children, footer }: CardProps) {
    const headingId = useId();

    return (
        <section className={styles.card} aria-labelledby={headingId}>
            <header className={styles.header}>
                <div>
                    <h2 id={headingId} className={styles.title}>
                        {title}
                    </h2>
                    {subtitle && <p className={styles.subtitle}>{subtitle}</p>}
                </div>
                {actions && <div className={styles.headerActions}>{actions}</div>}
            </header>

            <div className={styles.body}>{children}</div>

            {footer && <footer className={styles.footer}>{footer}</footer>}
        </section>
    );
}