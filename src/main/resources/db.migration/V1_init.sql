create table categories
(
    id bigint not null
        constraint categories_pkey
            primary key,
    name varchar(255)
);
insert into categories (name)
values ('Комплектующие'),
       ('Аксессуары'),
       ('Продукция');


create table products
(
    id    bigint primary key,
    title varchar(255),
    cost  decimal(10, 2)
        category_id bigint
        constraint categories__id
        references categories
);
insert into products (title, cost, category_id)
values ('Phone', 1500, 3),
       ('Notebook', 50000, 3),
       ('Tablet', 10000, 3),
       ('Bookkeeper', 8000, 3),
       ('MemoryCard', 1000, 2),
       ('Smartphone', 20000, 3),
       ('Computer', 70000, 3),
       ('TV', 40000, 3),
       ('Monitor', 10000, 3),
       ('Printer', 7000, 3),
       ('Mouse', 1000, 2),
       ('Keyboard', 1000, 2),
       ('Headphones', 3000, 2),
       ('Gamepad', 2500, 2),
       ('HardDisk', 3000, 1),
       ('SSD', 5000, 1),
       ('Processor', 18000, 1),
       ('Motherboard', 10000, 1),
       ('RAM', 2000, 1),
       ('VideoCard', 80000, 1),
       ('Router', 3000, 3);