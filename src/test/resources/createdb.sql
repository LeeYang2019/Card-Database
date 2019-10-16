create table collector
(
    id            int auto_increment,
    user_name     varchar(50) null,
    user_password varchar(50) null,
    constraint collector_id_uindex
        unique (id)
);

alter table collector
    add primary key (id);

create table yugioh_card
(
    id            int auto_increment,
    user_id       int         null,
    card_name     varchar(50) null,
    card_type     varchar(25) null,
    card_rarity   varchar(25) null,
    card_set      varchar(25) null,
    card_price    double      null,
    card_quantity int         null,
    constraint yugioh_card_id_uindex
        unique (id),
    constraint yugioh_card_collector_id_fk
        foreign key (user_id) references collector (id)
            on update cascade on delete cascade
);

alter table yugioh_card
    add primary key (id);

