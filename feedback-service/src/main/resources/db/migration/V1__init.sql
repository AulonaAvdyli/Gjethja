create table feedback (
    feedback_id bigint(20) primary key AUTO_INCREMENT,
    description varchar(255) default null,
    created_on DATETIME not null default current_timestamp on update current_timestamp
);