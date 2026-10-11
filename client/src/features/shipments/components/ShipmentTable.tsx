
import type { ShipmentView } from '../types';
import Badge, { type BadgeVariant } from '../../../components/ui/Badge';
import Card from '../../../components/ui/Card';

interface ShipmentTableProps {
    shipments: ShipmentView[];
}

function getStatusVariant(status: string): BadgeVariant {
    switch (status.toUpperCase()) {
        case 'RECEIVED':
            return 'success';
        case 'PENDING':
            return 'warning';
        case 'CANCELLED':
            return 'danger';
        default:
            return 'neutral';
    }
}

export default function ShipmentTable({ shipments }: ShipmentTableProps) {
    return (
        <Card
            title="Incoming Shipments"
            subtitle="Overview of shipment records"
        >
            <table>
                <thead>
                <tr>
                    <th>Shipment Name</th>
                    <th>Warehouse</th>
                    <th>Status</th>
                    <th>Expected Arrival</th>
                </tr>
                </thead>

                <tbody>
                {shipments.length === 0 ? (
                    <tr>
                        <td colSpan={4}>
                            No shipments found.
                        </td>
                    </tr>
                ) : (
                    shipments.map((shipment, index) => (
                        <tr key={shipment.id ?? index}>
                            <td>{shipment.shipmentName}</td>
                            <td>{shipment.warehouseName}</td>
                            <td>
                                <Badge variant={getStatusVariant(shipment.orderStatus)}>
                                    {shipment.orderStatus}
                                </Badge>
                            </td>
                            <td>
                                {shipment.expectedArrival
                                    ? new Date(shipment.expectedArrival).toLocaleString()
                                    : 'Not scheduled'}
                            </td>
                        </tr>
                    ))
                )}
                </tbody>
            </table>
        </Card>
    );
}
