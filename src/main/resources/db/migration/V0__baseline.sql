-- Baseline migration - initial database schema
CREATE TABLE users (
    id UUID NOT NULL,
    external_identity VARCHAR(255) NOT NULL,
    display_name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_external_identity UNIQUE (external_identity)
);
