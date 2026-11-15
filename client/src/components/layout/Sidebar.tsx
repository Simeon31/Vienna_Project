import { NavLink } from 'react-router';
import { NAV_ITEMS } from '../../config/navigation';
import styles from './Sidebar.module.css';

interface SidebarProps {
    open: boolean;
    onClose: () => void;
}

export default function Sidebar({ open, onClose }: SidebarProps) {
    {
        return (
            <aside id="sidebar" className={`${styles.sidebar} ${open ? styles.open : ''}`}>
                <div className={styles.brand}>
                    <span className={styles.logo} aria-hidden="true"/>
                    <div>
                        <strong>DNO</strong>
                        <small>Distribution Network Optimizer</small>
                    </div>
                </div>

                <nav aria-label="Main navigation">
                    <ul className={styles.list}>
                        {NAV_ITEMS.map((item) => (
                            <li key={item.path}>
                                <NavLink
                                    to={item.path}
                                    end={item.path === '/'}
                                    onClick={onClose}
                                    className={({isActive}) =>
                                        isActive ? `${styles.link} ${styles.active}` : styles.link
                                    }
                                >
                                    {item.label}
                                </NavLink>
                            </li>
                        ))}
                    </ul>
                </nav>

                <div className={styles.footer}>
                    <strong>Admin Workspace</strong>
                    <small>Network administration</small>
                </div>
            </aside>
        );
    }
}