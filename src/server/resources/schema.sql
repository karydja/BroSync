CREATE TABLE users (
   id       SERIAL PRIMARY KEY,
   username VARCHAR UNIQUE NOT NULL,
   email    VARCHAR UNIQUE NOT NULL,
   password VARCHAR NOT NULL
);

CREATE TABLE files (
   id             SERIAL PRIMARY KEY,
   original_dir   VARCHAR,
   original_path  VARCHAR NOT NULL,
   filename       VARCHAR NOT NULL
);

CREATE TABLE sharings (
   id       SERIAL PRIMARY KEY,
   user_id  INTEGER NOT NULL REFERENCES users(id),
   file_id  INTEGER NOT NULL REFERENCES files(id)
);
