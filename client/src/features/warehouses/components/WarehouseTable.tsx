import Table from '../../../components/ui/Table';
import Badge from '../../../components/ui/Badge';
import ProgressBar from '../../../components/ui/ProgressBar';
import ButtonLink from '../../../components/ui/ButtonLink';
import { getCapacityStatus, getCommittedPercent, getSampleUtilization } from '../capacity';
import type { Warehouse } from '../types';
import styles from './WarehouseTable.module.css';
import Button from '../../../components/ui/Button';

interface WarehouseTableProps {
    /** Warehouses to show, in the given order. */
    warehouses: Warehouse[];
    /** Called when the user clicks Activate or Deactivate in a row. */
    onToggleActive: (warehouse: Warehouse) => void;
}

export default function WarehouseTable({ warehouses, onToggleActive }: WarehouseTableProps) {
    return (
        <Table>
            <thead>
            <tr>
                <th scope="col">Warehouse Name</th>
                <th scope="col">Location</th>
                <th scope="col">Total Capacity</th>
                <th scope="col">Committed Capacity</th>
                <th scope="col">Available</th>
                <th scope="col">Status</th>
                <th scope="col">
                    <span className="visually-hidden">Actions</span>
                </th>
            </tr>
            </thead>
            <tbody>
            {warehouses.map((warehouse) => {
                const utilization = getSampleUtilization(warehouse.id);
                const committed = Math.round(getCommittedPercent(utilization));
                const status = getCapacityStatus(committed);
                const available = Math.max(0, 100 - committed);

                return (
                    <tr key={warehouse.id} className={warehouse.active ? undefined : styles.inactive}>
                        <td className={styles.name}>{warehouse.name}</td>
                        <td className={styles.location}>{warehouse.address}</td>
                        <td className={styles.number}>
                            {warehouse.capacity.toLocaleString('en-US')} m³
                        </td>
                        <td>
                            <div className={styles.committed}>
                                <ProgressBar
                                    label={`${warehouse.name} committed capacity`}
                                    primary={utilization.occupiedPercent}
                                    secondary={utilization.reservedPercent}
                                />
                                <span className={styles.percent}>{committed}%</span>
                                <Badge variant={status.variant}>{status.label}</Badge>
                            </div>
                        </td>
                        <td className={styles.number}>{available}%</td>
                        <td>
                            <Badge variant={warehouse.active ? 'success' : 'neutral'}>
                                {warehouse.active ? 'Active' : 'Inactive'}
                            </Badge>
                        </td>
                        <td>
                            <div className={styles.actions}>
                                <ButtonLink
                                    to={`/warehouses/${warehouse.id}`}
                                    variant="text"
                                    aria-label={`View Details ${warehouse.name}`}
                                >
                                    View Details
                                </ButtonLink>
                                <ButtonLink
                                    to={`/warehouses/${warehouse.id}/edit`}
                                    variant="text"
                                    aria-label={`Edit ${warehouse.name}`}
                                >
                                    Edit
                                </ButtonLink>
                                <Button
                                    variant="text"
                                    onClick={() => onToggleActive(warehouse)}
                                    aria-label={`${warehouse.active ? 'Deactivate' : 'Activate'} ${warehouse.name}`}
                                >
                                    {warehouse.active ? 'Deactivate' : 'Activate'}
                                </Button>
                            </div>
                        </td>
                    </tr>
                );
            })}
            </tbody>
        </Table>
    );
}