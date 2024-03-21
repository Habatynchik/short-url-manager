CREATE TABLE url
(
    id         bigserial primary key,
    long_url   VARCHAR      NOT NULL,
    short_code VARCHAR(100) NOT NULL
);

create TABLE users
(
    id       bigserial primary key,
    email    varchar(255) not null,
    password varchar(255) not null
);

CREATE TABLE url_info
(
    id           bigserial primary key,
    url_id       bigint not null,
    created_date date   not null,
    expired_date date,
    user_id      bigint default null,

    foreign key (url_id) references url (id),
    foreign key (user_id) references users (id)
);

