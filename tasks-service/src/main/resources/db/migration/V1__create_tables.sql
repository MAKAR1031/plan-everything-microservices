-- task_statuses table
CREATE TABLE task_statuses (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(20) NOT NULL,
  code VARCHAR(20) NOT NULL
);
CREATE UNIQUE INDEX task_statuses_name_uindex ON task_statuses (name);
CREATE UNIQUE INDEX task_statuses_code_uindex ON task_statuses (code);

-- tasks table
CREATE TABLE tasks (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(120) NOT NULL,
  description TEXT,
  expected_complete_date DATE,
  task_state_id BIGINT NOT NULL,
  member_author_id BIGINT NOT NULL,
  member_assignee_id BIGINT,
  project_id BIGINT NOT NULL,
  CONSTRAINT tasks_task_statuses_id_fk FOREIGN KEY (task_state_id) REFERENCES task_statuses (id) ON UPDATE CASCADE
);
CREATE INDEX tasks_name_index ON tasks (name);

-- task_steps table
CREATE TABLE task_steps (
  id BIGSERIAL NOT NULL,
  name VARCHAR(120) NOT NULL,
  step_order INT NOT NULL,
  completed BOOLEAN DEFAULT FALSE  NOT NULL,
  need_report BOOLEAN DEFAULT FALSE NOT NULL,
  report TEXT,
  task_id BIGINT NOT NULL,
  CONSTRAINT task_steps_tasks_id_fk FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX task_steps_name_index ON task_steps (name);
