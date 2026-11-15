import type { ComponentProps } from 'react';
import { Link } from 'react-router';
import type { ButtonVariant } from './Button';
import styles from './Button.module.css';

type ButtonLinkProps = ComponentProps<typeof Link> & {
    variant?: ButtonVariant;
};

export default function ButtonLink({ variant = 'secondary', className, ...rest }: ButtonLinkProps) {
    const classes = [styles.button, styles[variant], className].filter(Boolean).join(' ');

    return <Link className={classes} {...rest} />;
}