insert into role (id, name) values (1, 'HEAD_OF_PROCUREMENT');
insert into role (id, name) values (2, 'SELLER');
insert into role (id, name) values (3, 'WORKER');

insert into authority_table (name) values ('HEAD_OF_PROCUREMENT');
insert into authority_table (name) values ('SELLER');
insert into authority_table (name) values ('WORKER');

insert into role_authority (role_id, authority_id) values (1, 1);
insert into role_authority (role_id, authority_id) values (2, 2);
insert into role_authority (role_id, authority_id) values (3, 3);

insert into user_table (card_number, email, first_name, last_name, password) VALUES ('0001', 'admin@admin.com', 'Head', 'Head', '$2a$10$sfOM0tcraqVA6/3zfgS9HuiX7j41RYNBF/Wsm96xnhDFICeM1CR2O');
insert into user_table (card_number, email, first_name, last_name, password) VALUES ('0002', 'seller@seller.com', 'seller', 'Seller', 'seller');
insert into user_table (card_number, email, first_name, last_name, password) VALUES ('0003', 'worker@worker.com', 'worker', 'worker', 'worker');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 2);
insert into user_role (user_id, role_id) values (3, 3);