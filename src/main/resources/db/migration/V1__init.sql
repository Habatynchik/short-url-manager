CREATE TABLE url
(
    id         bigserial primary key,
    long_url   VARCHAR      NOT NULL,
    short_code VARCHAR(100) NOT NULL
);

create TABLE roles
(
    id   bigserial primary key,
    name varchar(255) not null
);

create TABLE users
(
    id                bigserial primary key,
    display_name      varchar(255) not null,
    email             varchar(255) not null,
    password          varchar(255),
    registration_date date,
    role_id           bigint,

    foreign key (role_id) references roles(id)
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

CREATE TABLE refresh_tokens
(
    id          bigserial primary key,
    token       varchar(255),
    expiry_date timestamptz,
    user_id     bigint,

    foreign key (user_id) references users (id)
);