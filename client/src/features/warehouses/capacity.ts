import type { BadgeVariant } from '../../components/ui/Badge';

export const WARNING_THRESHOLD = 75;
export const CRITICAL_THRESHOLD = 90;

export interface CapacityStatus {
    label: string;
    variant: BadgeVariant;
}

export function getCapacityStatus(committedPercent: number): CapacityStatus {
    if (committedPercent >= CRITICAL_THRESHOLD) {
        return { label: 'Critical', variant: 'danger' };
    }
    if (committedPercent >= WARNING_THRESHOLD) {
        return { label: 'Warning', variant: 'warning' };
    }
    return { label: 'Normal', variant: 'neutral' };
}