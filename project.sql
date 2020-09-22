drop database if exists project;
create database project;
use project;

create table users (
	userID INT(11) PRIMARY KEY auto_increment,
    username VARCHAR(30) not null,
    password VARCHAR(30) not null
);

insert into users (username, password)
	values('emusk27', 'password123'),
    ('narmstrong11', 'firstman'),
    ('annie', '123'),
    ('glor', '312'),
    ('lauren', '12345')