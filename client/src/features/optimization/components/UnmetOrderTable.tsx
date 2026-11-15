import Table from '../../../components/ui/Table';
import type { UnmetOrder } from '../types';
import styles from './UnmetOrderTable.module.css';

interface UnmetOrderTableProps {
    /** Orders no warehouse can supply in this run. */
    unmetOrders: UnmetOrder[];
}

export default function UnmetOrderTable({ unmetOrders }: UnmetOrderTableProps) {
    return (
        <Table>
            <thead>
            <tr>
                <th scope="col">Order</th>
                <th scope="col">Store</th>
                <th scope="col">Reason</th>
            </tr>
            </thead>
            <tbody>
            {unmetOrders.map((order) => (
                <tr key={order.orderId}>
                    <td className={styles.order}>{order.orderId}</td>
                    <td>{order.storeName}</td>
                    <td>{order.reason}</td>
                </tr>
            ))}
            </tbody>
        </Table>
    );
}