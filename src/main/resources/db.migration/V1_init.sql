create table products
(
    id    bigint primary key,
    title varchar(255),
    cost  decimal(10, 2)
);
insert into products (title, cost)
values ('Phone', 1500),
       ('Notebook', 50000),
       ('Tablet', 10000),
       ('Bookkeeper', 8000),
       ('MemoryCard', 1000),
       ('Smartphone', 20000),
       ('Computer', 70000),
       ('TV', 40000),
       ('Monitor', 10000),
       ('Printer', 7000),
       ('Mouse', 1000),
       ('Keyboard', 1000),
       ('Headphones', 3000),
       ('Gamepad', 2500),
       ('HardDisk', 3000),
       ('SSD', 5000),
       ('Processor', 18000),
       ('Motherboard', 10000),
       ('RAM', 2000),
       ('VideoCard', 80000),
       ('Router', 3000);