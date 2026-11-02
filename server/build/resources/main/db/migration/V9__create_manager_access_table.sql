CREATE TABLE IF NOT EXISTS manager_access (
    user_id              INTEGER NOT NULL,
    warehouse_management BOOLEAN NOT NULL DEFAULT FALSE,
    store_management     BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_manager_access_user
        FOREIGN KEY (user_id) REFERENCES "user" (user_id),
    CONSTRAINT chk_manager_access_at_least_one
        CHECK (warehouse_management OR store_management)
);
