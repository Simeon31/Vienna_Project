import { useId, type InputHTMLAttributes } from 'react';
import styles from './TextField.module.css';

interface TextFieldProps extends Omit<InputHTMLAttributes<HTMLInputElement>, 'id'> {
    /** Visible label above the field. */
    label: string;
    /** Small grey help text under the field. */
    hint?: string;
    /** Error message under the field. Also marks the field as invalid for screen readers. */
    error?: string;
}

/** Labelled text input with optional hint and error message. */
export default function TextField({ label, hint, error, required, className, ...rest }: TextFieldProps) {
    const id = useId();
    const hintId = `${id}-hint`;
    const errorId = `${id}-error`;
    const describedBy = [hint && hintId, error && errorId].filter(Boolean).join(' ') || undefined;
    const inputClasses = [styles.input, error && styles.invalid, className].filter(Boolean).join(' ');

    return (
        <div className={styles.field}>
            <label htmlFor={id} className={styles.label}>
                {label}
                {required && <span aria-hidden="true"> *</span>}
            </label>
            <input
                id={id}
                className={inputClasses}
                required={required}
                aria-invalid={error ? true : undefined}
                aria-describedby={describedBy}
                {...rest}
            />
            {hint && (
                <p id={hintId} className={styles.hint}>
                    {hint}
                </p>
            )}
            {error && (
                <p id={errorId} className={styles.error}>
                    {error}
                </p>
            )}
        </div>
    );
}