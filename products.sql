DROP TABLE IF EXISTS products CASCADE;
create table if not exists products
(
    id    bigint auto_increment primary key,
    title varchar(128)   null,
    cost  decimal(10, 2) null
);
INSERT INTO products (title, cost)
VALUES ('Phone', 20000),
       ('Notebook', 30000),
       ('Table', 15000),
       ('Bookreader', 10000),
       ('Powerbank', 3000);
