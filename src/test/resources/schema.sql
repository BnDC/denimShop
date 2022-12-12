-- auto-generated definition
create table denim
(
    denim_id     bigint auto_increment
        primary key,
    category     varchar(255) null,
    description  varchar(255) null,
    image        varchar(255) null,
    price        bigint       null,
    product_code varchar(255) null
);

-- auto-generated definition
create table item
(
    item_id     bigint auto_increment
        primary key,
    price       bigint       null,
    quantity    bigint       null,
    size_option varchar(255) null,
    denim_id    bigint       null,
    constraint FKbd79sc8m5ari60q1kmwlfm3f2
        foreign key (denim_id) references denim (denim_id)
);

-- auto-generated definition
create table orders
(
    order_id    bigint auto_increment
        primary key,
    create_at   datetime(6)  null,
    updated_at  datetime(6)  null,
    address     varchar(255) null,
    email       varchar(255) null,
    post_code   varchar(255) null,
    total_price bigint       null
);

-- auto-generated definition
create table order_item
(
    order_item_id bigint auto_increment
        primary key,
    item_id       bigint null,
    order_id      bigint null,
    constraint FKija6hjjiit8dprnmvtvgdp6ru
        foreign key (item_id) references item (item_id),
    constraint FKt4dc2r9nbvbujrljv3e23iibt
        foreign key (order_id) references orders (order_id)
);



