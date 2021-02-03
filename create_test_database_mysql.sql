create database xxlbet_test;
use xxlbet_test;

create table sports
(
    id   bigint primary key,
    name varchar(30) not null
)
    charset = latin1;