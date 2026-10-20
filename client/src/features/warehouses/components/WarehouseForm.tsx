import { useState, type ChangeEvent, type FormEvent } from 'react';
import Card from '../../../components/ui/Card';
import Button from '../../../components/ui/Button';
import TextField from '../../../components/ui/TextField';
import type { WarehouseFormValues, WarehouseInput } from '../types';
import styles from './WarehouseForm.module.css';

type FormErrors = Partial<Record<keyof WarehouseFormValues, string>>;

interface WarehouseStatusInfo {
    title: string;
    label: string;
    hint: string;
    note: string;
}

interface WarehouseFormProps {
    /** Values the form starts with: empty for "add", the current warehouse for "edit". */
    initialValues: WarehouseFormValues;
    /** Text of the submit button, e.g. "Add Warehouse". */
    submitLabel: string;
    /** Read-only status box under the fields. */
    status: WarehouseStatusInfo;
    /** Small note on the left of the buttons. */
    footerNote?: string;
    /** Called with validated values. Throw an Error to show its message in the form. */
    onSubmit: (input: WarehouseInput) => Promise<void>;
    onCancel: () => void;
}

// Accepts "51,44" as well as "51.44", because people type both
function parseNumber(value: string): number {
    return Number(value.trim().replace(',', '.'));
}

// Same rules as the Warehouse domain class in the backend
function validate(values: WarehouseFormValues): FormErrors {
    const errors: FormErrors = {};
    const latitude = parseNumber(values.latitude);
    const longitude = parseNumber(values.longitude);
    const capacity = parseNumber(values.capacity);

    if (!values.name.trim()) {
        errors.name = 'Warehouse name is required.';
    }
    if (!values.address.trim()) {
        errors.address = 'Location is required.';
    }
    if (!values.latitude.trim() || Number.isNaN(latitude) || latitude < -90 || latitude > 90) {
        errors.latitude = 'Latitude must be a number between -90 and 90.';
    }
    if (!values.longitude.trim() || Number.isNaN(longitude) || longitude < -180 || longitude > 180) {
        errors.longitude = 'Longitude must be a number between -180 and 180.';
    }
    if (!values.capacity.trim() || Number.isNaN(capacity) || capacity <= 0) {
        errors.capacity = 'Total capacity must be a number greater than 0.';
    }

    return errors;
}

function toInput(values: WarehouseFormValues): WarehouseInput {
    return {
        name: values.name.trim(),
        address: values.address.trim(),
        latitude: parseNumber(values.latitude),
        longitude: parseNumber(values.longitude),
        capacity: parseNumber(values.capacity),
    };
}

export default function WarehouseForm({
                                          initialValues,
                                          submitLabel,
                                          status,
                                          footerNote,
                                          onSubmit,
                                          onCancel,
                                      }: WarehouseFormProps) {
    const [values, setValues] = useState(initialValues);
    const [errors, setErrors] = useState<FormErrors>({});
    const [submitting, setSubmitting] = useState(false);
    const [submitError, setSubmitError] = useState<string | null>(null);

    const update = (field: keyof WarehouseFormValues) => (event: ChangeEvent<HTMLInputElement>) => {
        setValues((current) => ({ ...current, [field]: event.target.value }));
    };

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        const found = validate(values);
        setErrors(found);
        if (Object.keys(found).length > 0) return;

        setSubmitting(true);
        setSubmitError(null);

        try {
            await onSubmit(toInput(values));
        } catch (caught) {
            setSubmitError(caught instanceof Error ? caught.message : 'The warehouse could not be saved.');
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <form className={styles.form} onSubmit={handleSubmit} noValidate>
            <Card
                title="Warehouse Information"
                footer={
                    <>
                        <span>{footerNote}</span>
                        <div className={styles.buttons}>
                            <Button variant="secondary" onClick={onCancel} disabled={submitting}>
                                Cancel
                            </Button>
                            <Button type="submit" disabled={submitting}>
                                {submitting ? 'Saving…' : submitLabel}
                            </Button>
                        </div>
                    </>
                }
            >
                <div className={styles.fields}>
                    <TextField
                        label="Warehouse Name"
                        required
                        value={values.name}
                        onChange={update('name')}
                        error={errors.name}
                        placeholder="Enter warehouse name"
                    />
                    <TextField
                        label="Location"
                        required
                        value={values.address}
                        onChange={update('address')}
                        error={errors.address}
                        placeholder="Enter warehouse location"
                    />
                    <div className={styles.row}>
                        <TextField
                            label="Latitude"
                            required
                            inputMode="decimal"
                            value={values.latitude}
                            onChange={update('latitude')}
                            error={errors.latitude}
                            placeholder="e.g. 51.4416"
                        />
                        <TextField
                            label="Longitude"
                            required
                            inputMode="decimal"
                            value={values.longitude}
                            onChange={update('longitude')}
                            error={errors.longitude}
                            placeholder="e.g. 5.4697"
                        />
                    </div>
                    <TextField
                        label="Total Capacity (m³)"
                        required
                        inputMode="decimal"
                        value={values.capacity}
                        onChange={update('capacity')}
                        error={errors.capacity}
                        placeholder="Enter total storage capacity"
                        hint="Enter the warehouse's total storage capacity in cubic meters."
                    />

                    <div>
                        <p className={styles.statusTitle}>{status.title}</p>
                        <div className={styles.statusBox}>
                            <span className={styles.statusValue}>{status.label}</span>
                            <span className={styles.statusHint}>{status.hint}</span>
                        </div>
                        <p className={styles.statusNote}>{status.note}</p>
                    </div>

                    {submitError && (
                        <p className={styles.submitError} role="alert">
                            {submitError}
                        </p>
                    )}
                </div>
            </Card>
        </form>
    );
}