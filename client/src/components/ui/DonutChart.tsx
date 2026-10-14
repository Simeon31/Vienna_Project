import styles from './DonutChart.module.css';

export interface DonutSegment {
    label: string;
    value: number;
    color: string;
}

interface DonutChartProps {
    segments: DonutSegment[];
    centerLabel: string;
}

const RADIUS = 40;
const STROKE_WIDTH = 16;
const CIRCUMFERENCE = 2 * Math.PI * RADIUS;

export default function DonutChart({ segments, centerLabel }: DonutChartProps) {
    const total = segments.reduce((sum, segment) => sum + segment.value, 0);
    const description = segments.map((segment) => `${segment.label} ${segment.value}`).join(', ');

    const arcs = segments.map((segment, index) => {
        const valueBefore = segments
            .slice(0, index)
            .reduce((sum, previous) => sum + previous.value, 0);

        return {
            ...segment,
            length: total > 0 ? (segment.value / total) * CIRCUMFERENCE : 0,
            offset: total > 0 ? (valueBefore / total) * CIRCUMFERENCE : 0,
        };
    });

    return (
        <svg
            viewBox="0 0 120 120"
            className={styles.chart}
            role="img"
            aria-label={`${centerLabel} ${total}: ${description}`}
        >
            <circle
                cx="60"
                cy="60"
                r={RADIUS}
                fill="none"
                stroke="var(--color-bar-track)"
                strokeWidth={STROKE_WIDTH}
            />
            {arcs.map((arc) => (
                <circle
                    key={arc.label}
                    cx="60"
                    cy="60"
                    r={RADIUS}
                    fill="none"
                    stroke={arc.color}
                    strokeWidth={STROKE_WIDTH}
                    strokeDasharray={`${arc.length} ${CIRCUMFERENCE - arc.length}`}
                    strokeDashoffset={-arc.offset}
                    transform="rotate(-90 60 60)"
                />
            ))}
            <text x="60" y="55" textAnchor="middle" className={styles.centerLabel}>
                {centerLabel}
            </text>
            <text x="60" y="72" textAnchor="middle" className={styles.centerValue}>
                {total.toLocaleString('en-US')}
            </text>
        </svg>
    );
}