-- projects table
CREATE TABLE projects (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(120) NOT NULL,
  description TEXT,
  create_date DATE NOT NULL,
  opened BOOLEAN DEFAULT TRUE  NOT NULL,
  account_author_id BIGINT NOT NULL
);
CREATE UNIQUE INDEX projects_name_uindex ON projects (name);
