create table role_table
(
    id   int      not null
        constraint role_table_pk
            primary key,
    name varchar(20) not null
);

create table shop_users
(
    id       int not null
        constraint user_table_pk
            primary key,
    login    varchar(50),
    password varchar(500),
    role_id  integer
        constraint shop_users_role_table_id_fk
            references role_table
);

create
    unique index shop_users_login_uindex
    on shop_users (login);

insert into role_table(name)
values ('ROLE_ADMIN');
insert into role_table(name)
values ('ROLE_USER');


create table categories
(
    id    int primary key,
    title varchar(255)
);

insert into categories (title)
values ('Комплектующие'),
       ('Аксессуары'),
       ('Продукция');


create table products
(
    id          int primary key,
    title       varchar(255),
    category_id int,
    cost        decimal(10, 2)
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