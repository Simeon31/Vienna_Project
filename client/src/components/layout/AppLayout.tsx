import { useEffect, useState } from 'react';
import { Outlet } from 'react-router';
import Sidebar from './Sidebar';
import styles from './AppLayout.module.css';

export default function AppLayout() {
    const [menuOpen, setMenuOpen] = useState(false);
    const closeMenu = () => setMenuOpen(false);
    useEffect(() => {
        if (!menuOpen) return;

        const handleKeyDown = (event: KeyboardEvent) => {
            if (event.key === 'Escape') {
                setMenuOpen(false);
            }
        };

        window.addEventListener('keydown', handleKeyDown);
        return () => window.removeEventListener('keydown', handleKeyDown);
    }, [menuOpen]);

    return (
        <div className={styles.layout}>
            <header className={styles.topbar}>
                <button
                    type="button"
                    className={styles.burger}
                    aria-label="Open navigation"
                    aria-expanded={menuOpen}
                    aria-controls="sidebar"
                    onClick={() => setMenuOpen(true)}
                >
                    <span />
                    <span />
                    <span />
                </button>
                <span className={styles.brand}>DNO</span>
            </header>

            <Sidebar open={menuOpen} onClose={closeMenu} />

            {menuOpen && (
                <div className={styles.backdrop} onClick={closeMenu} aria-hidden="true" />
            )}

            <main className={styles.content}>
                <Outlet />
            </main>
        </div>
    );
}