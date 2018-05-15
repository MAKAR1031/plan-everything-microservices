-- event_types table
CREATE TABLE event_types (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  code VARCHAR(10) NOT NULL
);
CREATE UNIQUE INDEX event_types_code_uindex ON event_types (code);

-- task_events table
CREATE TABLE task_events (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(200) NOT NULL,
  event_time TIMESTAMP NOT NULL,
  event_type_id BIGINT NOT NULL,
  task_id BIGINT NOT NULL,
  initiator_id BIGINT NOT NULL,
  CONSTRAINT task_events_event_type_id_fk FOREIGN KEY (event_type_id) REFERENCES event_types (id)
);
