import PageHeader from '../../../components/ui/PageHeader';
import StatCard from '../../../components/ui/StatCard';
import styles from './DashboardPage.module.css';

export default function DashboardPage() {
    return (
        <>
            <PageHeader
                title="Dashboard Overview"
                subtitle="Administration and warehouse monitoring"/>

            <section className={styles.stats} aria-label="Key figures">
                <StatCard label="Total Accounts" value={248} hint="All registered users" />
                <StatCard label="Active Accounts" value={221} hint="89% of all accounts" />
                <StatCard label="Total Warehouses" value={16} hint="Across 9 regions" />
                <StatCard label="Warehouses Near Capacity" value={2} hint="At or above 75% committed"/>
            </section>
        </>
    );
}