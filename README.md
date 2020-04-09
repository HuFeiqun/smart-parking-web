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
alter table parking_space modify id int not null;



``` sql
create table parkinglot
(
	id int auto_increment,
	name varchar(100) null,
	address varchar(100) null,
	picurl varchar(500) null comment '停车场图片的网址',
	lng decimal(10,7) not null comment '经度',
	lat decimal(10,7) null comment '纬度',
	capacity int null comment '停车场容量',
	available int null comment '目前可用量',
	constraint parkinglot_pk
	primary key (id)
);
alter table parkinglot modify picurl varchar(1000) null comment '停车场图片的网址';

```

```
create table parking_space
(
	id int auto_increment,
	parkinglot_id int null comment '所在停车场的id',
	car_id varchar(50) not null comment '车牌号',
	in_use int default 0 null comment '标记使用状态,0不用 1用',
	park_time bigint null comment '进入停车场时间',
	constraint parking_space_pk
		primary key (id),
	constraint parkingspace_parkinglot_id_fk
		foreign key (parkinglot_id) references parkinglot (id)
)
comment '停车位数据表';

#设置联合主键
alter table parking_space drop primary key;

alter table  parking_space add primary key(id,parkinglot_id)

```

``` sql
#在停车位表中插入假数据
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1000,"浙A48602",1,1586440938715);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1001,"浙A79187",1,1586440938473);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1002,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1003,"浙A53140",1,1586440933513);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1004,"浙A51169",1,1586440933469);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1005,"浙A32968",1,1586440940429);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1006,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1007,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1008,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1009,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1010,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1011,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1012,"浙A19824",1,1586440941586);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1013,"浙A67227",1,1586440941394);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1014,"浙A67829",1,1586440933745);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1015,"浙A57704",1,1586440935164);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1016,"浙A42498",1,1586440941251);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1017,"浙A33247",1,1586440933862);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1018,"浙A98618",1,1586440938952);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1019,"浙A70325",1,1586440939432);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1020,"浙A53165",1,1586440938324);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1021,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1022,"浙A34125",1,1586440941432);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1023,"浙A56042",1,1586440936837);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1024,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1025,"浙A58029",1,1586440933382);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1026,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1027,"浙A65998",1,1586440936926);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1028,"浙A28223",1,1586440935054);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1029,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1030,"浙A42966",1,1586440940926);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1031,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1032,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1033,"浙A91970",1,1586440934613);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1034,"浙A94477",1,1586440941258);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1035,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1036,"浙A55314",1,1586440941115);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1037,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1038,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1039,"浙A59165",1,1586440939700);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1040,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1041,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1042,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1043,"浙A40233",1,1586440933819);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1044,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1045,"浙A39344",1,1586440935664);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1046,"浙A97339",1,1586440941859);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1047,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1048,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1049,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1050,"浙A66202",1,1586440936288);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1051,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1052,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1053,"浙A27506",1,1586440938053);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1054,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1055,"浙A34567",1,1586440933708);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1056,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1057,"浙A64015",1,1586440940662);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1058,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1059,"浙A68789",1,1586440933477);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1060,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1061,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1062,"浙A87379",1,1586440937328);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1063,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1064,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1065,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1066,"浙A36438",1,1586440937821);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1067,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1068,"浙A23905",1,1586440940556);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1069,"浙A10116",1,1586440934808);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1070,"浙A32269",1,1586440934825);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1071,"浙A13882",1,1586440940221);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1072,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1073,"浙A50826",1,1586440939061);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1074,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1075,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1076,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1077,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1078,"浙A74433",1,1586440939091);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1079,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1080,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1081,"浙A62675",1,1586440935779);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1082,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1083,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1084,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1085,"浙A44398",1,1586440937273);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1086,"浙A59210",1,1586440933274);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1087,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1088,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1089,"浙A25420",1,1586440933052);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1090,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1091,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1092,"浙A72441",1,1586440932882);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1093,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1094,"浙A17743",1,1586440937209);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1095,"浙A27484",1,1586440941325);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1096,"浙A12344",1,1586440933693);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1097,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1098,"浙A69144",1,1586440933001);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1099,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1100,"浙A12338",1,1586440939907);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1000,"浙A62991",1,1586440934076);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1001,"浙A89294",1,1586440940682);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1002,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1003,"浙A47869",1,1586440939873);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1004,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1005,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1006,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1007,"浙A33720",1,1586440941440);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1008,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1009,"浙A16060",1,1586440933005);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1010,"浙A47258",1,1586440938532);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1011,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1012,"浙A73631",1,1586440936492);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1013,"浙A88533",1,1586440937569);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1014,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1015,"浙A81615",1,1586440936084);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1016,"浙A17539",1,1586440936983);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1017,"浙A36806",1,1586440933984);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1018,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1019,"浙A47623",1,1586440940565);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1020,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1021,"浙A54622",1,1586440933377);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1022,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1023,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1024,"浙A20534",1,1586440940801);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1025,"浙A62374",1,1586440941592);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1026,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1027,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1028,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1029,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1030,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1031,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1032,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1033,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1034,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1035,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1036,"浙A67293",1,1586440940182);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1037,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1038,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1039,"浙A72062",1,1586440938706);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1040,"浙A56197",1,1586440940732);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1041,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1042,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1043,"浙A68377",1,1586440935505);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1044,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1045,"浙A61807",1,1586440941758);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1046,"浙A15976",1,1586440935157);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1047,"浙A91642",1,1586440933898);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1048,"浙A64236",1,1586440936172);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1049,"浙A63235",1,1586440939040);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1050,"浙A52263",1,1586440939088);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1051,"浙A28826",1,1586440934953);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1052,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1053,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1054,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1055,"浙A68347",1,1586440941450);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1056,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1057,"浙A43007",1,1586440941111);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1058,"浙A37441",1,1586440941565);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1059,"浙A83660",1,1586440940629);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1060,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1061,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1062,"浙A38139",1,1586440941123);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1063,"浙A82199",1,1586440936911);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1064,"浙A40701",1,1586440939369);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1065,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1066,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1067,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1068,"浙A30552",1,1586440941650);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1069,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1070,"浙A44480",1,1586440939319);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1071,"浙A41739",1,1586440939167);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1072,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1073,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1074,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1075,"浙A16291",1,1586440941371);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1076,"浙A82786",1,1586440933002);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1077,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1078,"浙A87082",1,1586440933628);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1079,"浙A94995",1,1586440938397);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1080,"浙A92794",1,1586440935380);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1081,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1082,"浙A62294",1,1586440934420);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1083,"浙A15164",1,1586440941301);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1084,"浙A14232",1,1586440937256);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1085,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1086,"浙A86808",1,1586440936777);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1087,"浙A88118",1,1586440937268);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1088,"浙A28011",1,1586440939039);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1089,"浙A39978",1,1586440933969);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1090,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1091,"浙A71237",1,1586440937322);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1092,"浙A66710",1,1586440940775);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1093,"浙A51032",1,1586440936192);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1094,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1095,"浙A57367",1,1586440935872);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1096,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1097,"浙A47642",1,1586440936026);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1098,"浙A43230",1,1586440935177);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1099,"浙A28948",1,1586440933940);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1100,"浙A36647",1,1586440940872);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1000,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1001,"浙A11020",1,1586440941792);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1002,"浙A86900",1,1586440935735);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1003,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1004,"浙A92866",1,1586440936931);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1005,"浙A23533",1,1586440938842);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1006,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1007,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1008,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1009,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1010,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1011,"浙A52704",1,1586440934048);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1012,"浙A65354",1,1586440936661);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1013,"浙A11586",1,1586440934317);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1014,"浙A99317",1,1586440940292);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1015,"浙A48350",1,1586440937667);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1016,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1017,"浙A16233",1,1586440936005);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1018,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1019,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1020,"浙A12670",1,1586440938913);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1021,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1022,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1023,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1024,"浙A11620",1,1586440940159);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1025,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1026,"浙A56076",1,1586440933082);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1027,"浙A42956",1,1586440940059);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1028,"浙A62700",1,1586440937215);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1029,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1030,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1031,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1032,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1033,"浙A45840",1,1586440936570);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1034,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1035,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1036,"浙A90741",1,1586440940734);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1037,"浙A31752",1,1586440941247);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1038,"浙A23532",1,1586440933866);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1039,"浙A83357",1,1586440937587);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1040,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1041,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1042,"浙A73637",1,1586440937678);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1043,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1044,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1045,"浙A91983",1,1586440938018);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1046,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1047,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1048,"浙A79203",1,1586440940026);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1049,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1050,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1051,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1052,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1053,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1054,"浙A95250",1,1586440938719);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1055,"浙A67731",1,1586440933589);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1056,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1057,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1058,"浙A50567",1,1586440938392);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1059,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1060,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1061,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1062,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1063,"浙A52502",1,1586440935925);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1064,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1065,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1066,"浙A44121",1,1586440934629);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1067,"浙A44317",1,1586440935518);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1068,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1069,"浙A60176",1,1586440935326);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1070,"浙A68962",1,1586440940731);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1071,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1072,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1073,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1074,"浙A28343",1,1586440937588);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1075,"浙A54532",1,1586440936094);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1076,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1077,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1078,"浙A97577",1,1586440932904);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1079,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1080,"浙A82681",1,1586440932935);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1081,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1082,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1083,"浙A88792",1,1586440936786);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1084,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1085,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1086,"浙A53326",1,1586440935734);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1087,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1088,"浙A42737",1,1586440940788);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1089,"浙A21581",1,1586440939470);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1090,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1091,"浙A31237",1,1586440937119);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1092,"浙A42098",1,1586440938893);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1093,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1094,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1095,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1096,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1097,"浙A74579",1,1586440940312);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1098,"浙A57181",1,1586440937153);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1099,"浙A43328",1,1586440938538);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1101,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1102,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1101,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1102,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1101,"",0,0);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1102,"",0,0);

```

引入My

执行select * from order会报错：
[42000][1064] You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'order' at line 1

原因：应该由于数据表名称order是查询的关键字之一（order by),引起歧义


### ESMAP用法
[配置地图](https://www.esmap.cn/escopemap/content/cn/develope/map-styleedit.html)