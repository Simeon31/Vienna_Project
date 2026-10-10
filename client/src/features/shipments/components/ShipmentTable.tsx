import type { Shipment } from '../types';
import Badge, { type BadgeVariant } from '../../../components/ui/Badge';
import Card from '../../../components/ui/Card';

interface ShipmentTableProps {
    shipments: Shipment[];
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
        <Card title="Incoming Shipments" subtitle="Overview of shipment records">
            <table>
                <thead>
                <tr>
                    <th>Shipment ID</th>
                    <th>Warehouse ID</th>
                    <th>Status</th>
                    <th>Expected Arrival</th>
                </tr>
                </thead>
                <tbody>
                {shipments.map((shipment, index) => (
                    <tr key={shipment.id ?? index}>
                        <td>{shipment.id ?? 'Not assigned'}</td>
                        <td>{shipment.warehouseId}</td>
                        <td>
                            <Badge variant={getStatusVariant(shipment.orderStatus)}>
                                {shipment.orderStatus}
                            </Badge>
                        </td>
                        <td>{shipment.expectedArrival ?? 'Not scheduled'}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </Card>
    );
}
