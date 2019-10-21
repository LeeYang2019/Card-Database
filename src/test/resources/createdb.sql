create table if not exists yugioh_player
(
    id              int auto_increment,
    player_name     varchar(50) not null,
    player_password varchar(50) not null,
    constraint yugioh_player_id_uindex
        unique (id)
);

alter table yugioh_player
    add constraint `PRIMARY`
        primary key (id);

create table if not exists yugioh_card
(
    id               int auto_increment,
    yugioh_player_id int         null,
    card_name        varchar(70) null,
    card_type        varchar(25) null,
    card_rarity      varchar(25) null,
    card_set         varchar(25) null,
    card_price       int         null,
    card_quantity    int         not null,
    constraint yugioh_card_id_uindex
        unique (id),
    constraint yugioh_card_yugioh_player_id_fk
        foreign key (yugioh_player_id) references yugioh_player (id)
            on update cascade on delete cascade
)
    charset = latin1;

create index yugioh_card_user_id_fk
    on yugioh_card (yugioh_player_id);

alter table yugioh_card
    add constraint `PRIMARY`
        primary key (id);

create table if not exists yugioh_card_history
(
    id             int auto_increment,
    yugioh_card_id int       not null,
    time_stamp     timestamp not null,
    quantity       int       null,
    price          double    null,
    constraint update_history_id_uindex
        unique (id),
    constraint update_history_update_date_uindex
        unique (time_stamp),
    constraint update_history_yugioh_card_id_fk
        foreign key (yugioh_card_id) references yugioh_card (id)
            on update cascade on delete cascade
);

alter table yugioh_card_history
    add constraint `PRIMARY`
        primary key (id);

