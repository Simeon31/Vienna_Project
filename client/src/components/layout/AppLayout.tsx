import { Outlet } from 'react-router';
import Sidebar from './Sidebar';
import styles from './AppLayout.module.css';

export default function AppLayout() {
    return (
        <div className={styles.layout}>
            <Sidebar />
            <main className={styles.content}>
                <Outlet />
            </main>
        </div>
    );
}