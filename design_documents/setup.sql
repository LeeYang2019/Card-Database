create schema if not exists yugioh_card_db collate utf8mb4_0900_ai_ci;

create table if not exists user
(
    id int auto_increment,
    user_name varchar(50) not null,
    user_password varchar(50) not null,
    user_role varchar(7) null,
    constraint user_id_uindex
        unique (id)
);

alter table user
    add primary key (id);

create table if not exists yugioh_card
(
    id int auto_increment,
    user_id int null,
    card_name varchar(70) null,
    card_type varchar(25) null,
    card_rarity varchar(9) null,
    card_set varchar(6) null,
    card_index varchar(5) null,
    price double null,
    qty int null,
    status varchar(8) null,
    image varchar(64) null,
    constraint yugioh_card_id_uindex
        unique (id),
    constraint yugioh_card_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    charset=latin1;

alter table yugioh_card
    add primary key (id);

create table if not exists yugioh_card_history
(
    id int auto_increment,
    yugiohCard_id int null,
    update_date timestamp not null,
    price double null,
    constraint update_history_id_uindex
        unique (id),
    constraint update_history_yugioh_card_id_fk
        foreign key (yugiohCard_id) references yugioh_card (id)
            on update cascade on delete cascade
);

alter table yugioh_card_history
    add primary key (id);

