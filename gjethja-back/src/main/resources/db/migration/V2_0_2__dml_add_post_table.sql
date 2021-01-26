create table post (
    post_id bigint(20) primary key AUTO_INCREMENT,
    title varchar(255) default null,
    description varchar(255) default null,
    created_on DATETIME not null default current_timestamp on update current_timestamp,
    status varchar(20),
    user_id bigint(20) not null ,
    foreign key (user_id) references users (user_id)
);