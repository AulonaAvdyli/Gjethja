create table posts(
    post_id bigint(20) primary key,
    title varchar(255) default null,
    description varchar(255) default null,
    created_on DATETIME not null default current_timestamp on update current_timestamp,
    status varchar(255),
    user varchar(255) default null,
    boost_duration DATE default null,
    is_boosted TINYINT(1) default 0
);