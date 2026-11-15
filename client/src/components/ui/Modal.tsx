import { useEffect, useId, useRef, type ReactNode } from 'react';
import styles from './Modal.module.css';

interface ModalProps {
    /** Whether the dialog is shown. The parent keeps this in state. */
    open: boolean;
    /** Called on Escape, backdrop click, the x button, or when the parent's buttons decide so. */
    onClose: () => void;
    /** Heading of the dialog, also used as its accessible name. */
    title: string;
    /** Short explanation under the title, read by screen readers together with the title. */
    description?: ReactNode;
    /** Optional content between description and buttons, e.g. a list of details. */
    children?: ReactNode;
    /** Buttons at the bottom right, e.g. Cancel and Confirm. */
    actions?: ReactNode;
}

/** Modal dialog based on the native <dialog> element: focus trap, Escape and backdrop for free. */
export default function Modal({ open, onClose, title, description, children, actions }: ModalProps) {
    const dialogRef = useRef<HTMLDialogElement>(null);
    const titleId = useId();
    const descriptionId = useId();

    // Keep the native dialog in sync with the "open" prop
    useEffect(() => {
        const dialog = dialogRef.current;
        if (!dialog) return;

        if (open && !dialog.open) dialog.showModal();
        if (!open && dialog.open) dialog.close();
    }, [open]);

    return (
        <dialog
            ref={dialogRef}
            className={styles.dialog}
            aria-labelledby={titleId}
            aria-describedby={description ? descriptionId : undefined}
            onCancel={(event) => {
                // Escape: let the parent decide by updating its state
                event.preventDefault();
                onClose();
            }}
            onClick={(event) => {
                // A click on the dialog element itself is a click on the backdrop
                if (event.target === event.currentTarget) onClose();
            }}
        >
            <div className={styles.content}>
                <div className={styles.header}>
                    <h2 id={titleId} className={styles.title}>
                        {title}
                    </h2>
                    <button type="button" className={styles.close} onClick={onClose} aria-label="Close dialog">
                        ×
                    </button>
                </div>

                {description && (
                    <p id={descriptionId} className={styles.description}>
                        {description}
                    </p>
                )}

                {children && <div className={styles.body}>{children}</div>}

                {actions && <div className={styles.actions}>{actions}</div>}
            </div>
        </dialog>
    );
}