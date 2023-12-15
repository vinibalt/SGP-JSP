create table model_login
(
    login            varchar(200)         null,
    senha            varchar(255)         not null,
    id               bigint unsigned auto_increment
        primary key,
    nome             varchar(300)         not null,
    email            varchar(300)         not null,
    admin            tinyint(1) default 0 not null,
    fotouser         longtext             null,
    extensaofotouser varchar(10)          null,
    admin2           int        default 0 null,
    constraint login_unique
        unique (login)
);

create table historico_acessos
(
    id_user bigint unsigned not null,
    data    timestamp       null,
    constraint historico_acessos_ibfk_1
        foreign key (id_user) references model_login (id)
);

create index id_user
    on historico_acessos (id_user);

