-- criteria table
CREATE TABLE criteria (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(120) NOT NULL,
  criterion_order INT NOT NULL,
  expected_value INT NOT NULL,
  actual_value INT,
  task_id BIGINT NOT NULL
);
CREATE INDEX criteria_name_index ON criteria (name);
