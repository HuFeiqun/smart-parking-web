``` sql
#命令行运行mysql执行创建数据库命令
create database parking

create table user
(
	id int auto_increment,
	username varchar(20) null,
	password varchar(50) null,
	token char(36) null,
	constraint user_pk
		primary key (id)
);

create table `order`
(
	id varchar(50) primary key,
	carId varchar(20) null,
	appointTime date null,
	charge float null,
	startDate DATE null,
	startHour int null,
	endDate DATE null,
	endHour int null,
	constraint order_pk
		primary key (id)
);


load data local infile 'C:\Users\86137\Desktop\创4\parking.csv' into table order fields terminated by ',';
alter table `order` modify startDate varchar(20) null;

alter table `order` modify endDate varchar(20) null;

alter table `order` modify appointTime varchar(50) null;

rename table `order` to parkOrder;  //重命名表格


```


``` 公告
create table notice
(
	id int auto_increment,
	title varchar(50) null,
	`desc` varchar(1000) null,
	tags varchar(100) null,
	gmt_create bigint null,
	gmt_modified bigint null,
	constraint notice_pk
		primary key (id)
)
comment '公告';


```
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate


引入My

执行select * from order会报错：
[42000][1064] You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'order' at line 1

原因：应该由于数据表名称order是查询的关键字之一（order by),引起歧义
