import Table from '../../../components/ui/Table';
import { formatEuro, formatKm } from '../plan';
import type { Assignment } from '../types';
import styles from './AssignmentTable.module.css';

interface AssignmentTableProps {
    /** Plan lines to show, one per order. */
    assignments: Assignment[];
}

export default function AssignmentTable({ assignments }: AssignmentTableProps) {
    return (
        <Table>
            <thead>
            <tr>
                <th scope="col">Order</th>
                <th scope="col">Store</th>
                <th scope="col">Warehouse</th>
                <th scope="col" className={styles.number}>Distance</th>
                <th scope="col" className={styles.number}>Transport Cost</th>
            </tr>
            </thead>
            <tbody>
            {assignments.map((assignment) => (
                <tr key={assignment.orderId}>
                    <td className={styles.order}>{assignment.orderId}</td>
                    <td>{assignment.storeName}</td>
                    <td>{assignment.warehouseName}</td>
                    <td className={styles.number}>{formatKm(assignment.distanceKm)}</td>
                    <td className={styles.number}>{formatEuro(assignment.transportCost)}</td>
                </tr>
            ))}
            </tbody>
        </Table>
    );
}