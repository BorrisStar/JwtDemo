create table users
(
    id         bigserial primary key,
    username   varchar(255) not null unique,
    password   varchar(255) not null,
    email      varchar(255) unique,
    first_name varchar(255),
    last_name  varchar(255),
    status     varchar(25) DEFAULT 'ACTIVE',
    created    date   DEFAULT now(),
    updated    date   DEFAULT now()

);

create table roles
(
    id      bigserial primary key,
    name    varchar(255) not null,
    status  varchar(25) DEFAULT 'ACTIVE',
    created date   DEFAULT now(),
    updated date   DEFAULT now()

);

create table user_roles
(
    user_id bigint,
    role_id bigint
);

alter table if exists user_roles
    add constraint user_roles_user_fk
        foreign key (user_id) references users;

alter table if exists user_roles
    add constraint user_roles_roles_fk
        foreign key (role_id) references roles;
