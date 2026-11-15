import styles from './SearchInput.module.css';

interface SearchInputProps {
    /** Read by screen readers, e.g. "Search warehouses". Not shown visually. */
    label: string;
    /** Current text. The parent keeps it in state. */
    value: string;
    /** Called with the new text on every keystroke. */
    onChange: (value: string) => void;
    /** Grey hint inside the empty field. */
    placeholder?: string;
}

export default function SearchInput({ label, value, onChange, placeholder }: SearchInputProps) {
    return (
        <label className={styles.field}>
            <span className="visually-hidden">{label}</span>
            <input
                type="search"
                className={styles.input}
                value={value}
                onChange={(event) => onChange(event.target.value)}
                placeholder={placeholder}
            />
        </label>
    );
}