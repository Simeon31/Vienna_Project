import { Link } from 'react-router';
import Card from '../../../components/ui/Card';
import styles from './QuickActionsPanel.module.css';

interface QuickAction {
    label: string;
    to?: string;
}

// Actions without a target are shown as disabled until their page exists
const QUICK_ACTIONS: QuickAction[] = [
    { label: 'Create Account' },
    { label: 'Add Warehouse', to: '/warehouses' },
    { label: 'Manage Accounts' },
    { label: 'View Warehouses', to: '/warehouses' },
];

export default function QuickActionsPanel() {
    return (
        <Card title="Quick Actions">
            <ul className={styles.grid}>
                {QUICK_ACTIONS.map((action) => (
                    <li key={action.label}>
                        {action.to ? (
                            <Link to={action.to} className={`${styles.tile} ${styles.link}`}>
                                <span>{action.label}</span>
                                <span className={styles.chevron} aria-hidden="true">
                  ›
                </span>
                            </Link>
                        ) : (
                            <div className={`${styles.tile} ${styles.disabled}`} aria-disabled="true">
                <span>
                  {action.label}
                    <small className={styles.hint}>Coming soon</small>
                </span>
                            </div>
                        )}
                    </li>
                ))}
            </ul>
        </Card>
    );
}