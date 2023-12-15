create table model_login
(
    login varchar(200) null,
    senha varchar(255) not null,
    id    bigint unsigned auto_increment
        primary key,
    nome  varchar(300) not null,
    email varchar(300) not null,
    constraint login_unique
        unique (login)
);


