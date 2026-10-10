
import PageHeader from '../../../components/ui/PageHeader';
import ShipmentTable from '../components/ShipmentTable';
import type { Shipment } from '../types';

export default function ShipmentsPage() {

    const shipments: Shipment[] = [
        {
            id: 1,
            warehouseId: 5,
            orderStatus: 'PENDING',
            expectedArrival: '2026-10-12T14:30:00',
            createdAt: '2026-10-10T10:00:00'
        },

    ];

    return (
        <>
            <PageHeader
                title="Shipment Management"
                subtitle="View and manage incoming shipments."
            />

            <ShipmentTable shipments={shipments} />
        </>
    );
}
