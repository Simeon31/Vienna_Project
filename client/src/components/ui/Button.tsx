import type { ButtonHTMLAttributes } from 'react';
import styles from './Button.module.css';

export type ButtonVariant = 'primary' | 'secondary' | 'danger' | 'text';

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
    variant?: ButtonVariant;
}

export default function Button({
    variant = 'primary',
    type = 'button',
    className,
    ...rest
}: ButtonProps) {
    const classes = [styles.button, styles[variant], className].filter(Boolean).join(' ');
    return <button type={type} className={classes} {...rest} />;
}