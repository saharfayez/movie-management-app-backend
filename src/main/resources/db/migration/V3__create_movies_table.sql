create table movies
(
    imdb_id      VARCHAR(50) PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    release_year VARCHAR(50)  NOT NULL,
    type         VARCHAR(50)  NOT NULL,
    poster       TEXT         NOT NULL
);



