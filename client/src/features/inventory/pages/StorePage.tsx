import { useMemo, useState, type FormEvent } from 'react';
import PageHeader from '../../../components/ui/PageHeader';
import type { Store } from '../types';
import styles from './StorePage.module.css';

type StoreRecord = Store & {
    deleted: boolean;
};

type StoreForm = Omit<Store, 'id'>;
type StoreModalMode = 'creating' | 'editing' | 'viewing' | null;

const initialStores: StoreRecord[] = [
    {
        id: 1,
        name: 'Central Market',
        address: '12 Ringstrasse',
        city: 'Vienna',
        warehouseID: 101,
        capacity: 850,
        deleted: false,
    },
    {
        id: 2,
        name: 'West End Grocers',
        address: '45 Mariahilfer Strasse',
        city: 'Vienna',
        warehouseID: 102,
        capacity: 620,
        deleted: false,
    },
    {
        id: 3,
        name: 'North Point Store',
        address: '8 Prager Strasse',
        city: 'Vienna',
        warehouseID: 103,
        capacity: 410,
        deleted: true,
    },
];

const warehouseOptions = [
    { id: 101, name: 'Warehouse 101' },
    { id: 102, name: 'Warehouse 102' },
    { id: 103, name: 'Warehouse 103' },
];

const emptyForm: StoreForm = {
    name: '',
    address: '',
    city: '',
    warehouseID: 0,
    capacity: 0,
};

export default function StorePage() {
    const [stores, setStores] = useState<StoreRecord[]>(initialStores);
    const [search, setSearch] = useState('');
    const [showDeleted, setShowDeleted] = useState(false);
    const [form, setForm] = useState<StoreForm>(emptyForm);
    const [editingId, setEditingId] = useState<number | null>(null);
    const [selectedStore, setSelectedStore] = useState<StoreRecord | null>(null);
    const [storeModalMode, setStoreModalMode] = useState<StoreModalMode>(null);
    const [deletingStore, setDeletingStore] = useState<StoreRecord | null>(null);

    const visibleStores = useMemo(() => {
        const query = search.trim().toLowerCase();

        return stores.filter((store) => {
            if (store.deleted !== showDeleted) return false;
            if (!query) return true;

            return [store.name, store.address, store.city, String(store.warehouseID)]
                .join(' ')
                .toLowerCase()
                .includes(query);
        });
    }, [search, showDeleted, stores]);

    const resetForm = () => {
        setForm(emptyForm);
        setEditingId(null);
        setSelectedStore(null);
        setStoreModalMode(null);
    };

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        if (!form.name.trim() || !form.address.trim() || !form.city.trim()) return;

        if (editingId !== null) {
            setStores((current) =>
                current.map((store) =>
                    store.id === editingId ? { ...store, ...form, deleted: false } : store,
                ),
            );
        } else {
            setStores((current) => [
                ...current,
                {
                    ...form,
                    id: Math.max(0, ...current.map((store) => store.id)) + 1,
                    deleted: false,
                },
            ]);
        }

        resetForm();
        setShowDeleted(false);
    };

    const startEditing = (store: StoreRecord) => {
        setEditingId(store.id);
        setForm({
            name: store.name,
            address: store.address,
            city: store.city,
            warehouseID: store.warehouseID,
            capacity: store.capacity,
        });
        setShowDeleted(false);
        setSelectedStore(store);
        setStoreModalMode('editing');
    };

    const openCreateForm = () => {
        setForm(emptyForm);
        setEditingId(null);
        setSelectedStore(null);
        setStoreModalMode('creating');
    };

    const restoreStore = (id: number) => {
        setStores((current) =>
            current.map((store) => (store.id === id ? { ...store, deleted: false } : store)),
        );
    };

    const confirmDelete = () => {
        if (!deletingStore) return;

        setStores((current) =>
            current.map((store) =>
                store.id === deletingStore.id ? { ...store, deleted: true } : store,
            ),
        );
        setDeletingStore(null);
    };

    return (
        <>
            <PageHeader
                title="Store Management"
                subtitle="Add and manage store locations."
                actions={
                    <button className={styles.primaryButton} type="button" onClick={openCreateForm}>
                        + New store
                    </button>
                }
            />

            <section className={styles.tableCard} aria-labelledby="store-list-title">
                <div className={styles.toolbar}>
                    <div>
                        <h2 id="store-list-title">Stores</h2>
                        <p>{visibleStores.length} {showDeleted ? 'deleted' : 'active'} records</p>
                    </div>
                    <div className={styles.filters}>
                        <label className={styles.searchLabel}>
                            <span className={styles.visuallyHidden}>Search stores</span>
                            <input
                                type="search"
                                value={search}
                                onChange={(event) => setSearch(event.target.value)}
                                placeholder="Search stores..."
                            />
                        </label>
                        <label className={styles.checkbox}>
                            <input
                                type="checkbox"
                                checked={showDeleted}
                                onChange={(event) => setShowDeleted(event.target.checked)}
                            />
                            Show deleted
                        </label>
                    </div>
                </div>

                <div className={styles.tableWrap}>
                    <table>
                        <thead>
                            <tr>
                                <th>Store</th>
                                <th>Location</th>
                                <th>Warehouse</th>
                                <th>Capacity</th>
                                <th><span className={styles.visuallyHidden}>Actions</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            {visibleStores.map((store) => (
                                <tr key={store.id}>
                                    <td>
                                        <strong>{store.name}</strong>
                                        <span className={styles.recordId}>ID {store.id}</span>
                                    </td>
                                    <td>{store.address}, {store.city}</td>
                                    <td>{store.warehouseID}</td>
                                    <td>{store.capacity.toLocaleString()}</td>
                                    <td>
                                        <div className={styles.actions}>
                                            <button
                                                className={styles.textButton}
                                                type="button"
                                                onClick={() => {
                                                    setSelectedStore(store);
                                                    setStoreModalMode('viewing');
                                                }}
                                            >
                                                View
                                            </button>
                                            {!store.deleted && (
                                                <button className={styles.textButton} type="button" onClick={() => startEditing(store)}>
                                                    Edit
                                                </button>
                                            )}
                                            <button
                                                className={styles.dangerButton}
                                                type="button"
                                                onClick={() =>
                                                    store.deleted
                                                        ? restoreStore(store.id)
                                                        : setDeletingStore(store)
                                                }
                                            >
                                                {store.deleted ? 'Restore' : 'Delete'}
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                    {visibleStores.length === 0 && (
                        <p className={styles.emptyState}>No stores match your search.</p>
                    )}
                </div>
            </section>

            {storeModalMode && (
                <div className={styles.modalBackdrop} role="presentation" onClick={resetForm}>
                    <section
                        className={`${styles.modal} ${storeModalMode === 'viewing' ? '' : styles.formModal}`}
                        role="dialog"
                        aria-modal="true"
                        aria-labelledby="store-modal-title"
                        onClick={(event) => event.stopPropagation()}
                    >
                        <div className={styles.sectionHeader}>
                            <div>
                                <h2 id="store-modal-title">
                                    {storeModalMode === 'viewing'
                                        ? selectedStore?.name
                                        : editingId === null
                                          ? 'Add a store'
                                          : 'Update store'}
                                </h2>
                                <p>
                                    {storeModalMode === 'viewing'
                                        ? 'Store details'
                                        : 'Enter the store details below.'}
                                </p>
                            </div>
                            <button className={styles.closeButton} type="button" onClick={resetForm} aria-label="Close store dialog">
                                ×
                            </button>
                        </div>
                        {storeModalMode === 'viewing' && selectedStore ? (
                            <dl className={styles.details}>
                                <div><dt>Address</dt><dd>{selectedStore.address}</dd></div>
                                <div><dt>City</dt><dd>{selectedStore.city}</dd></div>
                                <div><dt>Warehouse</dt><dd>{selectedStore.warehouseID}</dd></div>
                                <div><dt>Capacity</dt><dd>{selectedStore.capacity.toLocaleString()}</dd></div>
                                <div><dt>Status</dt><dd>{selectedStore.deleted ? 'Deleted' : 'Active'}</dd></div>
                            </dl>
                        ) : (
                            <form className={styles.form} onSubmit={handleSubmit}>
                            <label>
                                Store name
                                <input
                                    required
                                    value={form.name}
                                    onChange={(event) => setForm({ ...form, name: event.target.value })}
                                    placeholder="e.g. Central Market"
                                />
                            </label>
                            <label>
                                Address
                                <input
                                    required
                                    value={form.address}
                                    onChange={(event) => setForm({ ...form, address: event.target.value })}
                                    placeholder="Street and number"
                                />
                            </label>
                            <label>
                                City
                                <input
                                    required
                                    value={form.city}
                                    onChange={(event) => setForm({ ...form, city: event.target.value })}
                                    placeholder="e.g. Vienna"
                                />
                            </label>
                            <label>
                                Warehouse
                                <select
                                    required
                                    value={form.warehouseID || ''}
                                    onChange={(event) =>
                                        setForm({ ...form, warehouseID: Number(event.target.value) })
                                    }
                                >
                                    <option value="" disabled>
                                        Select warehouse
                                    </option>
                                    {warehouseOptions.map((warehouse) => (
                                        <option key={warehouse.id} value={warehouse.id}>
                                            {warehouse.name} ({warehouse.id})
                                        </option>
                                    ))}
                                </select>
                            </label>
                            <label>
                                Capacity
                                <input
                                    required
                                    min="0"
                                    type="number"
                                    value={form.capacity || ''}
                                    onChange={(event) =>
                                        setForm({ ...form, capacity: Number(event.target.value) })
                                    }
                                />
                            </label>
                                <div className={styles.modalActions}>
                                    <button className={styles.secondaryButton} type="button" onClick={resetForm}>
                                        Cancel
                                    </button>
                                    <button className={styles.primaryButton} type="submit">
                                        {editingId === null ? 'Add store' : 'Save changes'}
                                    </button>
                                </div>
                            </form>
                        )}
                    </section>
                </div>
            )}

            {deletingStore && (
                <div className={styles.modalBackdrop} role="presentation" onClick={() => setDeletingStore(null)}>
                    <section
                        className={styles.modal}
                        role="alertdialog"
                        aria-modal="true"
                        aria-labelledby="delete-store-title"
                        aria-describedby="delete-store-description"
                        onClick={(event) => event.stopPropagation()}
                    >
                        <div className={styles.sectionHeader}>
                            <div>
                                <h2 id="delete-store-title">Delete store?</h2>
                                <p id="delete-store-description">
                                    This will hide <strong>{deletingStore.name}</strong> from the active stores list.
                                </p>
                            </div>
                            <button
                                className={styles.closeButton}
                                type="button"
                                onClick={() => setDeletingStore(null)}
                                aria-label="Close delete confirmation"
                            >
                                ×
                            </button>
                        </div>
                        <div className={styles.modalActions}>
                            <button className={styles.secondaryButton} type="button" onClick={() => setDeletingStore(null)}>
                                Cancel
                            </button>
                            <button className={styles.confirmDeleteButton} type="button" onClick={confirmDelete}>
                                Delete store
                            </button>
                        </div>
                    </section>
                </div>
            )}
        </>
    );
}