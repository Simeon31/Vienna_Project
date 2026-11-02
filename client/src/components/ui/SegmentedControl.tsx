import { useId } from 'react';
import styles from './SegmentedControl.module.css';

interface SegmentedOption<T extends string> {
    value: T;
    label: string;
}

interface SegmentedControlProps<T extends string> {
    /** Name of the group, shown above the options, e.g. "Status". */
    label: string;
    /** Options from left to right. */
    options: SegmentedOption<T>[];
    /** Value of the selected option. */
    value: T;
    /** Called with the value of the newly selected option. */
    onChange: (value: T) => void;
}

export default function SegmentedControl<T extends string>({
                                                               label,
                                                               options,
                                                               value,
                                                               onChange,
                                                           }: SegmentedControlProps<T>) {
    const name = useId();

    return (
        <fieldset className={styles.group}>
            <legend className={styles.legend}>{label}</legend>
            <div className={styles.options}>
                {options.map((option) => (
                    <label key={option.value} className={styles.option}>
                        <input
                            type="radio"
                            name={name}
                            value={option.value}
                            checked={value === option.value}
                            onChange={() => onChange(option.value)}
                            className={styles.input}
                        />
                        <span className={styles.text}>{option.label}</span>
                    </label>
                ))}
            </div>
        </fieldset>
    );
}