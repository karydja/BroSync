CREATE TABLE users (
   id       SERIAL PRIMARY KEY,
   username VARCHAR UNIQUE NOT NULL,
   email    VARCHAR UNIQUE NOT NULL,
   password VARCHAR NOT NULL
);

CREATE TABLE files (
   id                                  SERIAL PRIMARY KEY,
   original_dir                        VARCHAR,
   original_path_within_original_dir   VARCHAR NOT NULL,
   newest_timestamp                    TIMESTAMP,
   filename                            VARCHAR NOT NULL
);

CREATE TABLE sharings (
   id                SERIAL PRIMARY KEY,
   user_id           INTEGER NOT NULL REFERENCES users(id),
   file_id           INTEGER NOT NULL REFERENCES files(id),
   dir_path_for_user VARCHAR,
   path_for_user     VARCHAR
);
