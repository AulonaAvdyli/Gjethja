create table certificates (
    id varchar(36) primary key ,
    file_name varchar(255) default null ,
    content_type varchar(36) default null ,
    data longblob default null ,
    provider_id bigint(20) not null ,
    foreign key (provider_id) references providers(user_id)
);