create table if not exists company
(
Id int primary key auto_increment,
company_name varchar(255) not null
);

create table if not exists Employee
(
id int primary key auto_increment,
name varchar(255) not null,
age int,
salary int,
company_Id int,
foreign key (company_Id) references company(Id)
);