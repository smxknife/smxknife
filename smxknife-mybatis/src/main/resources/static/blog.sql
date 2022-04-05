create table blog (
    id bigint not null auto_increment ,
    title varchar(30) not null,
    author_id bigint not null,
    date_time timestamp not null,
    primary key (id)
) engine=InnoDB default charset=utf8;

create table author (
    id bigint not null auto_increment ,
    name varchar(30) not null,
    sex smallint not null,
    address varchar(300),
    primary key (id)
) engine=InnoDB default charset=utf8;
