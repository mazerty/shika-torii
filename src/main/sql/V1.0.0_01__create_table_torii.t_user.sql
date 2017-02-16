CREATE TABLE torii.t_user (
  id       SERIAL,
  email    TEXT    NOT NULL,
  password TEXT    NOT NULL,
  admin    BOOLEAN NOT NULL,

  PRIMARY KEY (id),
  UNIQUE (email)
);

INSERT INTO torii.t_user (email, password, admin)
VALUES ('admin', '$2a$13$byD4Ftv39Z76hUfd01URsePSdaV722c7J7NcLfs6o3KdJsAwHhEjq', TRUE);
