create table collector
(
    id            int auto_increment,
    user_name     varchar(50) not null,
    user_password varchar(50) not null,
    constraint user_id_uindex
        unique (id)
);

alter table collector
    add primary key (id);

create table update_history
(
    id          int auto_increment,
    update_date datetime not null,
    quantity    int      null,
    price       double   null,
    constraint update_history_id_uindex
        unique (id),
    constraint update_history_update_date_uindex
        unique (update_date)
);

alter table update_history
    add primary key (id);

create table yugioh_card
(
    id            int auto_increment,
    collector_id  int         null,
    card_name     varchar(70) null,
    card_type     varchar(25) null,
    card_rarity   varchar(25) null,
    card_set      varchar(25) null,
    card_price    int         null,
    card_quantity int         not null,
    constraint yugioh_card_id_uindex
        unique (id),
    constraint yugioh_card_collector_id_fk
        foreign key (collector_id) references collector (id)
            on update cascade on delete cascade
)
    charset = latin1;

create index yugioh_card_user_id_fk
    on yugioh_card (collector_id);

alter table yugioh_card
    add primary key (id);

