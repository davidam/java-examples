

create table people (
	people_id integer primary key,
	first varchar(1000) not null,
	last varchar(2000),
	address varchar(4000),
	email varchar(2000) unique,
	dni varchar(2000) unique
);

