DROP TABLE IF EXISTS accident;
DROP TABLE IF EXISTS accident_types;
DROP TABLE IF EXISTS rules;
DROP TABLE IF EXISTS accident_and_rule;


CREATE TABLE accident_types
(
    id serial primary key,
    name TEXT
);

INSERT INTO accident_types (name) VALUES ('Две машины'), ('Машина и человек'), ('Машина и велосипед');

CREATE TABLE accident
(
    id serial primary key,
    name TEXT,
    "text" TEXT,
    address TEXT,
    accident_type_id int REFERENCES accident_types(id)
);

CREATE TABLE rules
(
    id serial primary key,
    name TEXT
);

INSERT INTO rules (name) VALUES ('Статья 1'), ('Статья 2'), ('Статья 3');

CREATE TABLE accident_and_rule
(
    accident_id int references accident(id),
    rule_id int references rules(id)
);