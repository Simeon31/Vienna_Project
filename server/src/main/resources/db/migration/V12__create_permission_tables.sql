CREATE TABLE permission (
                            permission_id SERIAL PRIMARY KEY,
                            permission_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE user_permission (
                                 user_id INTEGER NOT NULL,
                                 permission_id INTEGER NOT NULL,

                                 PRIMARY KEY (user_id, permission_id),

                                 CONSTRAINT fk_user_permission_user
                                     FOREIGN KEY (user_id)
                                         REFERENCES "user"(user_id)
                                         ON DELETE CASCADE,

                                 CONSTRAINT fk_user_permission_permission
                                     FOREIGN KEY (permission_id)
                                         REFERENCES permission(permission_id)
                                         ON DELETE CASCADE
);

INSERT INTO permission (permission_name)
VALUES
    ('WAREHOUSE_MANAGEMENT'),
    ('STORE_BRANCH_MANAGEMENT');