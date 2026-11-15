
import PageHeader from '../../../components/ui/PageHeader';
import ShipmentTable from '../components/ShipmentTable';
import type { ShipmentView } from '../types';

export default function ShipmentsPage() {
    const shipments: ShipmentView[] = [
        {
            id: 1,
            shipmentName: 'Electronics Restock',
            warehouseName: 'Amsterdam North',
            orderStatus: 'PENDING',
            expectedArrival: '2026-10-12T14:30:00'
        }
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
