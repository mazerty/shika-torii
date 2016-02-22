CREATE TABLE torii.t_user (
  id       INTEGER NOT NULL,
  email    TEXT    NOT NULL,
  password TEXT    NOT NULL,

  PRIMARY KEY (id),
  UNIQUE (email)
);

CREATE SEQUENCE torii.s_user_id;

INSERT INTO torii.t_user (id, email, password)
VALUES (nextval('torii.s_user_id'), 'admin', '$2a$13$byD4Ftv39Z76hUfd01URsePSdaV722c7J7NcLfs6o3KdJsAwHhEjq');
