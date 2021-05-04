CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY,
    authority TEXT NOT NULL UNIQUE
);

CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     TEXT  NOT NULL UNIQUE,
    password     TEXT NOT NULL,
    enabled      BOOLEAN DEFAULT TRUE,
    authority_id INT NOT NULL REFERENCES authorities (id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('root', '$2a$10$XcXlSse5foHH8pKDiiOt6uZq3OkEbMTO9GbYPZrfZV4FN2KNxaLMq',
        (select id from authorities where authority = 'ROLE_ADMIN'));