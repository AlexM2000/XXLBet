create table roles
(
    id   bigint auto_increment
        primary key,
    name varchar(10) not null
)
    charset = utf8;

create table sports
(
    id   bigint auto_increment
        primary key,
    name varchar(30) not null
)
    charset = latin1;

create table statuses
(
    id   bigint auto_increment
        primary key,
    name varchar(30) not null
)
    charset = utf8;

create table tournaments
(
    id           bigint auto_increment
        primary key,
    sport_id     bigint                             not null,
    name         varchar(30)                        not null,
    date_started datetime default CURRENT_TIMESTAMP null,
    date_ended   datetime                           not null,
    constraint tournaments_sports_id_fk
        foreign key (sport_id) references sports (id)
            on update cascade
)
    charset = utf8;

create table matches
(
    id               bigint auto_increment
        primary key,
    tournament_id    bigint                             not null,
    draw_coefficient decimal(2, 1)                      not null,
    date_started     datetime default CURRENT_TIMESTAMP not null,
    constraint matches_tournaments_id_fk
        foreign key (tournament_id) references tournaments (id)
            on update cascade on delete cascade
)
    charset = utf8;

create table opponents
(
    id            bigint auto_increment
        primary key,
    match_id      bigint        null,
    name          varchar(30)   not null,
    coefficient   decimal(2, 1) null,
    tournament_id bigint        null,
    constraint opponents_name_uindex
        unique (name),
    constraint opponents_matches_id_fk
        foreign key (match_id) references matches (id)
            on update cascade on delete set null,
    constraint opponents_tournaments_id_fk
        foreign key (tournament_id) references tournaments (id)
            on update cascade on delete cascade
)
    charset = utf8;

create table match_results
(
    id        bigint auto_increment
        primary key,
    match_id  bigint not null,
    winner_id bigint null,
    constraint match_results_match_id_uindex
        unique (match_id),
    constraint match_results_matches_id_fk
        foreign key (match_id) references matches (id)
            on delete cascade,
    constraint match_results_opponents_id_fk
        foreign key (winner_id) references opponents (id)
            on delete cascade
)
    charset = utf8;

create table users
(
    id           bigint auto_increment
        primary key,
    email        varchar(30)          not null,
    phone_number varchar(30)          not null,
    password     varchar(150)         not null,
    enabled      tinyint(1) default 0 not null,
    constraint users_email_uindex
        unique (email),
    constraint users_phone_number_uindex
        unique (phone_number)
)
    charset = utf8;

create table bets
(
    id                 bigint auto_increment
        primary key,
    match_id           bigint                             not null,
    result_id          bigint                             null,
    date_created       datetime default CURRENT_TIMESTAMP not null,
    sum                decimal(4, 2)                      not null,
    expected_winner_id bigint                             not null,
    user_id            bigint                             not null,
    constraint bets_match_results_id_fk
        foreign key (result_id) references match_results (id),
    constraint bets_matches_id_fk
        foreign key (match_id) references matches (id)
            on update cascade,
    constraint bets_opponents_id_fk
        foreign key (expected_winner_id) references opponents (id),
    constraint bets_users_id_fk
        foreign key (user_id) references users (id)
)
    charset = utf8;

create index bets_date_created_index
    on bets (date_created);

create index bets_match_id_result_id_expected_winner_id_user_id_index
    on bets (match_id, result_id, expected_winner_id, user_id);

create table credit_cards
(
    number  varchar(16) not null,
    thru    varchar(5)  not null,
    cvv     varchar(3)  not null,
    user_id bigint      not null,
    primary key (number),
    constraint credit_cards_users_id_fk
        foreign key (user_id) references users (id)
            on update cascade on delete cascade
);

create index credit_cards_user_id_index
    on credit_cards (user_id);

create table user_info
(
    email             varchar(30)                             not null,
    phone_number      varchar(20)                             not null,
    surname           varchar(30)                             not null,
    name              varchar(30)                             not null,
    second_name       varchar(30)                             not null,
    birth_date        date                                    not null,
    registration_date datetime      default CURRENT_TIMESTAMP not null,
    balance           decimal(4, 2) default 0.00              not null,
    status_id         bigint        default 0                 not null,
    profile_img_path  varchar(300)                            null,
    role_id           bigint        default 0                 not null,
    constraint user_info_email_uindex
        unique (email),
    constraint user_info_phone_number_uindex
        unique (phone_number),
    constraint user_info_roles_id_fk
        foreign key (role_id) references roles (id)
            on update cascade,
    constraint user_info_statuses_id_fk
        foreign key (status_id) references statuses (id)
            on update cascade,
    constraint user_info_users_email_fk
        foreign key (email) references users (email)
            on update cascade on delete cascade,
    constraint user_info_users_phone_number_fk
        foreign key (phone_number) references users (phone_number)
            on update cascade on delete cascade
)
    charset = utf8;

alter table user_info
    add primary key (email);

create table verification_tokens
(
    id         bigint auto_increment
        primary key,
    token      varchar(100)                        not null,
    expires_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    user_id    bigint                              not null,
    constraint verification_tokens_users_id_fk
        foreign key (user_id) references users (id)
            on update cascade on delete cascade
)
    charset = utf8;


