CREATE TABLE role (
                      role_id INTEGER PRIMARY KEY,
                      rolename VARCHAR(50) NOT NULL
);

INSERT INTO role (role_id, rolename) VALUES
                                         (1, 'ADMIN'),
                                         (2, 'MANAGER'),
                                         (3, 'EMPLOYEE');

CREATE TABLE "user" (
                        user_id SERIAL PRIMARY KEY,
                        username VARCHAR(100) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        password_hash VARCHAR(255) NOT NULL,
                        active BOOLEAN NOT NULL,
                        role_id INTEGER NOT NULL,

                        CONSTRAINT fk_user_role
                            FOREIGN KEY (role_id)
                                REFERENCES role(role_id)
);