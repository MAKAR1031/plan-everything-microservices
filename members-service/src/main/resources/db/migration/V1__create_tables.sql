-- member_privileges table
CREATE TABLE member_privileges (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(80) NOT NULL
);
CREATE UNIQUE INDEX member_privileges_name_uindex ON member_privileges (name);
CREATE UNIQUE INDEX member_privileges_code_uindex ON member_privileges (code);

-- member_roles table
CREATE TABLE member_roles (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(80) NOT NULL,
    code VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX member_roles_name_uindex ON member_roles (name);
CREATE UNIQUE INDEX member_roles_code_uindex ON member_roles (code);

-- member_role_privilege table
CREATE TABLE member_role_privilege (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  member_role_id BIGINT NOT NULL,
  member_privilege_id BIGINT NOT NULL,
  CONSTRAINT member_role_privilege_member_roles_id_fk FOREIGN KEY (member_role_id) REFERENCES member_roles (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT member_role_privilege_member_privileges_id_fk FOREIGN KEY (member_privilege_id) REFERENCES member_privileges (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- members table
CREATE TABLE members (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  project_id BIGINT NOT NULL,
  account_id BIGINT NOT NULL,
  member_role_id BIGINT NOT NULL,
  CONSTRAINT members_member_roles_id_fk FOREIGN KEY (member_role_id) REFERENCES member_roles (id)
);
