CREATE TABLE IF NOT EXISTS account (
    id UUID DEFAULT uuid_generate_v4 (),
    username VARCHAR(255) NOT NULL,
    system_id VARCHAR(255) NOT NULL,
    identity_id UUID REFERENCES identity(id),
    PRIMARY KEY(id),
    CONSTRAINT username_system_id UNIQUE (username, system_id)
);
