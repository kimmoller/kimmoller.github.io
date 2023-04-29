CREATE TABLE IF NOT EXISTS account (
    id UUID DEFAULT uuid_generate_v4 (),
    username VARCHAR(255) NOT NULL,
    system_id VARCHAR(255) NOT NULL,
    creation_time TIMESTAMPTZ NOT NULL,
    creation_provision_time TIMESTAMPTZ,
    creation_commit_time TIMESTAMPTZ,
    identity_id UUID REFERENCES identity(id),
    PRIMARY KEY(id),
    CONSTRAINT username_system_id UNIQUE (username, system_id),
    CONSTRAINT identity_id_system_id UNIQUE (identity_id, system_id)
);

CREATE INDEX account_username ON account(username);
CREATE INDEX account_identity_id_username ON account(identity_id, username);
CREATE INDEX account_identity_id_system_id ON account(identity_id, system_id);
CREATE INDEX account_creation_creation_provision_time ON account(creation_provision_time);
CREATE INDEX account_creation_commit_time ON account(creation_commit_time);