create table if not exists user
(
    id            int auto_increment,
    user_name     varchar(50) not null,
    user_password varchar(50) not null,
    constraint user_id_uindex
        unique (id)
);

alter table user
    add constraint `PRIMARY`
        primary key (id);

create table if not exists yugioh_card
(
    id        int auto_increment,
    user_id   int         null,
    card_name varchar(70) null,
    card_type varchar(25) null,
    constraint yugioh_card_id_uindex
        unique (id),
    constraint yugioh_card_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
)
    charset = latin1;

alter table yugioh_card
    add constraint `PRIMARY`
        primary key (id);

create table if not exists yugioh_card_history
(
    id            int auto_increment,
    yugiohCard_id int       not null,
    update_date   timestamp not null,
    price         double    null,
    constraint update_history_id_uindex
        unique (id),
    constraint update_history_update_date_uindex
        unique (update_date),
    constraint update_history_yugioh_card_id_fk
        foreign key (yugiohCard_id) references yugioh_card (id)
            on update cascade on delete cascade
);

alter table yugioh_card_history
    add constraint `PRIMARY`
        primary key (id);

