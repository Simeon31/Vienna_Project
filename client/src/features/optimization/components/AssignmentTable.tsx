import Table from '../../../components/ui/Table';
import Badge from '../../../components/ui/Badge';
import { ASSIGNMENT_RESULT, formatEuro } from '../plan';
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
                <th scope="col">Warehouse</th>
                <th scope="col" className={styles.number}>Coverage</th>
                <th scope="col" className={styles.number}>Cost</th>
                <th scope="col">Result</th>
            </tr>
            </thead>
            <tbody>
            {assignments.map((assignment) => {
                const result = ASSIGNMENT_RESULT[assignment.result];

                return (
                    <tr key={assignment.orderId}>
                        <td className={styles.order}>{assignment.orderId}</td>
                        <td>{assignment.warehouseName}</td>
                        <td className={styles.number}>{assignment.coveragePercent}%</td>
                        <td className={styles.number}>{formatEuro(assignment.cost)}</td>
                        <td>
                            <Badge variant={result.variant}>{result.label}</Badge>
                        </td>
                    </tr>
                );
            })}
            </tbody>
        </Table>
    );
}