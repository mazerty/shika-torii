CREATE TABLE torii.t_application (
  id   INTEGER NOT NULL,
  name TEXT    NOT NULL,

  PRIMARY KEY (id),
  UNIQUE (name)
);

INSERT INTO torii.t_application (id, name)
VALUES (1, 'torii');
