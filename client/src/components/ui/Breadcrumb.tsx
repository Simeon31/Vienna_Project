import { Link } from 'react-router';
import styles from './Breadcrumb.module.css';

export interface BreadcrumbItem {
    /** Text of the step, e.g. "Warehouses". */
    label: string;
    /** Link target. The last item is the current page and is never a link. */
    to?: string;
}

interface BreadcrumbProps {
    /** Steps from the top level down to the current page. */
    items: BreadcrumbItem[];
}

/** Shows where the current page sits, e.g. "Warehouses > Add Warehouse". */
export default function Breadcrumb({ items }: BreadcrumbProps) {
    return (
        <nav aria-label="Breadcrumb" className={styles.breadcrumb}>
            <ol className={styles.list}>
                {items.map((item, index) => {
                    const isCurrent = index === items.length - 1;

                    return (
                        <li key={item.label} className={styles.item}>
                            {item.to && !isCurrent ? (
                                <Link to={item.to} className={styles.link}>
                                    {item.label}
                                </Link>
                            ) : (
                                <span className={styles.current} aria-current={isCurrent ? 'page' : undefined}>
                  {item.label}
                </span>
                            )}
                        </li>
                    );
                })}
            </ol>
        </nav>
    );
}