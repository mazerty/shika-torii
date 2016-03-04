CREATE TABLE torii.t_user_application (
  user_fk        INTEGER NOT NULL,
  application_fk INTEGER NOT NULL,

  PRIMARY KEY (user_fk, application_fk),
  FOREIGN KEY (user_fk) REFERENCES torii.t_user (id),
  FOREIGN KEY (application_fk) REFERENCES torii.t_application (id)
);

INSERT INTO torii.t_user_application (user_fk, application_fk)
VALUES ((SELECT id
         FROM torii.t_user
         WHERE email = 'admin'), 1);
