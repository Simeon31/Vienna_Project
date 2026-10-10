export interface NavItem {
    label: string;
    path: string;
}

export const NAV_ITEMS: NavItem[] = [
    { label: 'Dashboard', path: '/' },
    { label: 'Warehouses', path: '/warehouses' },
    { label: 'Shipments', path: '/shipments' },
    { label: 'Inventory', path: '/inventory' },
];