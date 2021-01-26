create table password_reset_token (
    id bigint(20) primary key auto_increment,
    token varchar(36) not null,
    user_id bigint(20) not null,
    foreign key (user_id) references users(user_id)
);