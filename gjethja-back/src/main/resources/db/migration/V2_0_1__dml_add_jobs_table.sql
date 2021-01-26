create table jobs (
    job_id bigint(20) primary key AUTO_INCREMENT,
    job_name varchar(50) not null

);


create table provider_jobs (
    job_id bigint(20) not null,
    user_id bigint(20) not null,
    foreign key (user_id) references providers(user_id),
    foreign key (job_id) references jobs (job_id)

);
insert into jobs (job_name) values ('housekeeper');
insert into jobs (job_name) values ('babysitter');
insert into jobs (job_name) values ('eldercare');
insert into jobs (job_name) values ('petcare');


