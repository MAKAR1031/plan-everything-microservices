-- tags table
CREATE TABLE tags (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(120) NOT NULL,
  color VARCHAR(6) NOT NULL,
  project_id BIGINT NOT NULL
);
CREATE INDEX tags_name_index ON tags (name);
