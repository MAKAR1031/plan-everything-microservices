-- account_roles table
CREATE TABLE account_roles (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(20) NOT NULL,
  code VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX account_roles_code_uindex ON account_roles (code);
CREATE UNIQUE INDEX account_roles_name_uindex ON account_roles (name);

-- accounts table
CREATE TABLE accounts (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  login VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  full_name VARCHAR(255) NOT NULL,
  blocked BOOLEAN DEFAULT FALSE,
  account_role_id BIGINT,
  CONSTRAINT accounts_account_roles_id_fk FOREIGN KEY (account_role_id) REFERENCES account_roles (id)
);
CREATE UNIQUE INDEX accounts_login_uindex ON accounts (login);
CREATE UNIQUE INDEX accounts_email_uindex ON accounts (email);
