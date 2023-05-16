insert into roles (id, name)
values (1, 'ROLE_ADMIN');

insert into roles (id, name)
values (2, 'ROLE_USER');


insert into users (id, username, password, email, first_name, last_name, status)
values (1, 'borris', '$2a$08$KzrsAmQ3k0MrvDt.NK.WS.Jm.9eY.1/vg4nsuFFuIunqP4HG9Y61K', 'qwerty@yandex.ru', 'boris',
        'star', 'ACTIVE');
insert into users (id, username, password, email, first_name, last_name, status)
values (2, 'user', '$2a$08$KzrsAmQ3k0MrvDt.NK.WS.Jm.9eY.1/vg4nsuFFuIunqP4HG9Y61K', 'user@yandex.ru', 'user', 'test',
        'ACTIVE');

insert into user_roles (user_id, role_id)
values (1, 1);

insert into user_roles (user_id, role_id)
values (1, 2);

insert into user_roles (user_id, role_id)
values (2, 2);

