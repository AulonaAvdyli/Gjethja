create table users (
    user_id bigint(20) primary key AUTO_INCREMENT,
    first_name varchar(20) default null,
    last_name varchar(20) default null,
    email varchar(40) not null unique ,
    password varchar(100) not null,
    date_of_birth date default null,
    phone_number varchar(20) default null,
    gender varchar(10) default null,
    city varchar(20) default null,
    bio varchar(255) default null,
    is_enabled bit(1) default 0,
    profile_picture longblob default null
);

create table providers (
    user_id bigint(20) not null,
    certificate longblob default null,
    rate varchar(20) default null,
    education varchar (255) default null,
    foreign KEY (user_id) references users(user_id)
);

create table seekers (
    user_id bigint(20) not null,
    rate varchar(20) default null,
    foreign KEY (user_id) references users(user_id)
);

create table roles (
    role_id bigint(20) PRIMARY KEY auto_increment,
    role_name VARCHAR(50) not null
);

create table user_roles (
    role_id bigint(20) not null,
    user_id bigint(20) not null,
    foreign KEY (user_id) references users (user_id),
    foreign KEY (role_id) references roles (role_id)
);

create table confirmation_token (
    id bigint(20) PRIMARY KEY AUTO_INCREMENT,
    token varchar(36) not null,
    created_on DATETIME not null default current_timestamp on update current_timestamp,
    user_id bigint(20) not null,
    foreign KEY (user_id) references users(user_id)
);

insert into roles (role_name) values ('ROLE_USER');
insert into roles (role_name) values ('ROLE_ADMIN');

