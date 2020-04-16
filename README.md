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
	`description` varchar(1000) null,
	tags varchar(100) null,
	gmt_create bigint null,
	gmt_modified bigint null,
	constraint notice_pk
		primary key (id)
)
comment '公告';

alter table notice change `description` description varchar(1000) null;
alter table notice add comment_count int null comment '回复数';


```
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate


```
#创建评论表
create table comment
(
	id int auto_increment,
	type int not null comment '1表示一级回复,2表示二级回复',
	parent_id int not null comment '表示公告id，或父回复id',
	gmt_create long not null,
	comment_count int default 0 null comment '表示本条评论的回复条数',
	content varchar(500) not null comment '回复内容',
	constraint comment_pk
		primary key (id)
)
comment '回复表';

```

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
假如停车位空闲，则carId 和 停车时间 保存上次的停车时间【离开时间】
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

alter table parking_space modify id int not null;


#设置联合主键
alter table parking_space drop primary key;

alter table  parking_space add primary key(id,parkinglot_id)

```

``` sql
#在停车位表中插入假数据
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1000,"浙A68366",0,1587018545331);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1001,"浙A33897",1,1587018546662);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1002,"浙A50830",1,1587018544332);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1003,"浙A82733",1,1587018545068);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1004,"浙A28575",1,1587018543951);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1005,"浙A22872",1,1587018549986);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1006,"浙A67265",0,1587018544492);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1007,"浙A85167",1,1587018545815);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1008,"浙A89141",0,1587018549933);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1009,"浙A49459",0,1587018545733);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1010,"浙A52644",1,1587018544208);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1011,"浙A82368",1,1587018547222);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1012,"浙A95057",0,1587018544052);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1013,"浙A87872",1,1587018548699);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1014,"浙A48144",1,1587018544653);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1015,"浙A75355",0,1587018544261);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1016,"浙A93599",0,1587018542409);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1017,"浙A59497",0,1587018550327);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1018,"浙A52258",1,1587018544045);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1019,"浙A93317",0,1587018542774);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1020,"浙A85718",0,1587018544529);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1021,"浙A17970",0,1587018549802);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1022,"浙A15586",1,1587018544799);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1023,"浙A51321",0,1587018542912);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1024,"浙A57202",0,1587018546091);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1025,"浙A58501",1,1587018544875);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1026,"浙A66655",0,1587018550046);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1027,"浙A75733",0,1587018548228);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1028,"浙A51360",0,1587018543283);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1029,"浙A38810",0,1587018543900);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1030,"浙A81549",0,1587018547117);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1031,"浙A77285",1,1587018545539);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1032,"浙A87882",0,1587018549035);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1033,"浙A68198",1,1587018547998);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1034,"浙A86069",0,1587018543723);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1035,"浙A70132",0,1587018545299);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1036,"浙A54020",0,1587018546986);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1037,"浙A21361",0,1587018545826);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1038,"浙A98533",1,1587018544454);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1039,"浙A39506",0,1587018542512);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1040,"浙A24028",1,1587018549088);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1041,"浙A54586",1,1587018548179);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1042,"浙A54134",0,1587018543103);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1043,"浙A40866",1,1587018545125);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1044,"浙A91124",0,1587018543213);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1045,"浙A23921",0,1587018547534);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1046,"浙A18059",0,1587018548137);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1047,"浙A19580",1,1587018549484);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1048,"浙A42961",1,1587018548945);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1049,"浙A27824",0,1587018549698);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1050,"浙A64245",1,1587018543244);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1051,"浙A62324",0,1587018546612);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1052,"浙A16744",0,1587018551038);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1053,"浙A10626",1,1587018545910);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1054,"浙A39795",1,1587018544538);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1055,"浙A29433",0,1587018545063);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1056,"浙A33756",1,1587018550628);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1057,"浙A27914",0,1587018545114);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1058,"浙A61642",1,1587018542424);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1059,"浙A61418",0,1587018546567);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1060,"浙A15080",1,1587018546420);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1061,"浙A80664",1,1587018550656);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1062,"浙A32077",1,1587018549626);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1063,"浙A42821",1,1587018545020);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1064,"浙A10786",1,1587018542501);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1065,"浙A81605",0,1587018543257);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1066,"浙A73175",0,1587018543035);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1067,"浙A62423",1,1587018550074);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1068,"浙A43780",0,1587018542330);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1069,"浙A44856",0,1587018547077);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1070,"浙A67062",0,1587018550436);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1071,"浙A20644",0,1587018544358);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1072,"浙A86846",0,1587018550739);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1073,"浙A58453",1,1587018545481);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1074,"浙A29278",1,1587018549073);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1075,"浙A12712",1,1587018545259);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1076,"浙A17216",1,1587018543473);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1077,"浙A88790",1,1587018548442);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1078,"浙A66257",0,1587018543222);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1079,"浙A99861",1,1587018542288);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1080,"浙A77236",0,1587018545900);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1081,"浙A96449",1,1587018549684);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1082,"浙A65123",1,1587018543771);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1083,"浙A90438",1,1587018548405);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1084,"浙A92338",1,1587018543042);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1085,"浙A54190",0,1587018546192);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1086,"浙A84673",1,1587018542646);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1087,"浙A58341",1,1587018547875);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1088,"浙A88665",0,1587018550951);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1089,"浙A40175",1,1587018542276);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1090,"浙A50447",0,1587018545701);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1091,"浙A77739",0,1587018544056);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1092,"浙A82211",1,1587018546826);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1093,"浙A22701",0,1587018542184);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1094,"浙A84330",1,1587018542311);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1095,"浙A70419",1,1587018542956);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1096,"浙A49330",0,1587018547947);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1097,"浙A46880",1,1587018544144);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1098,"浙A90054",1,1587018542451);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1099,"浙A95491",1,1587018547550);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (1,1100,"浙A14869",0,1587018544670);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1000,"浙A48758",1,1587018547813);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1001,"浙A50372",1,1587018548545);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1002,"浙A20497",1,1587018547598);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1003,"浙A29064",0,1587018543225);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1004,"浙A38445",1,1587018549331);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1005,"浙A69479",1,1587018548688);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1006,"浙A68190",1,1587018547171);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1007,"浙A97588",0,1587018545061);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1008,"浙A60735",1,1587018549599);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1009,"浙A65529",1,1587018546146);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1010,"浙A64464",1,1587018544163);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1011,"浙A70901",1,1587018545761);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1012,"浙A19635",0,1587018543892);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1013,"浙A32799",0,1587018547211);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1014,"浙A79998",0,1587018548827);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1015,"浙A79206",0,1587018543371);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1016,"浙A72042",0,1587018546655);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1017,"浙A71156",1,1587018549563);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1018,"浙A21263",1,1587018544771);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1019,"浙A77920",1,1587018544104);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1020,"浙A65583",1,1587018547215);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1021,"浙A52100",0,1587018546303);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1022,"浙A25524",1,1587018547932);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1023,"浙A95600",1,1587018543996);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1024,"浙A56353",0,1587018547083);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1025,"浙A55619",0,1587018548696);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1026,"浙A59393",0,1587018544717);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1027,"浙A51763",0,1587018546999);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1028,"浙A63934",0,1587018543755);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1029,"浙A70578",0,1587018543479);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1030,"浙A41379",1,1587018547939);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1031,"浙A65940",0,1587018547457);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1032,"浙A33650",0,1587018543231);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1033,"浙A83232",0,1587018543301);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1034,"浙A35662",0,1587018543142);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1035,"浙A11657",1,1587018543629);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1036,"浙A39578",0,1587018548929);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1037,"浙A43763",0,1587018543460);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1038,"浙A77077",1,1587018544656);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1039,"浙A39838",0,1587018546177);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1040,"浙A36249",0,1587018546360);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1041,"浙A49528",0,1587018542669);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1042,"浙A85989",0,1587018549003);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1043,"浙A25341",0,1587018543083);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1044,"浙A22402",1,1587018546668);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1045,"浙A95880",1,1587018550940);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1046,"浙A46316",0,1587018546328);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1047,"浙A48012",1,1587018543507);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1048,"浙A96506",1,1587018543138);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1049,"浙A61071",0,1587018549084);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1050,"浙A50567",0,1587018542158);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1051,"浙A85744",1,1587018544490);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1052,"浙A60984",1,1587018546864);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1053,"浙A70813",0,1587018546514);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1054,"浙A42214",1,1587018544107);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1055,"浙A11569",0,1587018545235);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1056,"浙A60366",0,1587018549034);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1057,"浙A20578",0,1587018550217);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1058,"浙A18176",1,1587018546101);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1059,"浙A39786",0,1587018547915);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1060,"浙A17670",1,1587018549695);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1061,"浙A39683",0,1587018550797);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1062,"浙A32541",0,1587018543368);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1063,"浙A17545",1,1587018542603);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1064,"浙A43450",0,1587018544498);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1065,"浙A80570",1,1587018543450);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1066,"浙A66977",0,1587018543643);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1067,"浙A68174",0,1587018542941);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1068,"浙A62960",0,1587018543556);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1069,"浙A19522",0,1587018546620);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1070,"浙A93511",0,1587018548043);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1071,"浙A30798",1,1587018548620);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1072,"浙A28838",0,1587018545539);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1073,"浙A51319",1,1587018549824);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1074,"浙A91559",1,1587018549624);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1075,"浙A77558",0,1587018544589);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1076,"浙A19623",1,1587018544114);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1077,"浙A60036",1,1587018548848);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1078,"浙A13174",0,1587018549887);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1079,"浙A24889",0,1587018549172);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1080,"浙A98257",1,1587018545050);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1081,"浙A11307",1,1587018546063);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1082,"浙A88575",1,1587018549412);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1083,"浙A78910",0,1587018545935);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1084,"浙A49489",1,1587018544694);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1085,"浙A77962",1,1587018542156);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1086,"浙A87986",0,1587018545357);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1087,"浙A97055",1,1587018543199);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1088,"浙A20829",1,1587018545003);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1089,"浙A70963",0,1587018546886);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1090,"浙A35752",1,1587018549768);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1091,"浙A48498",0,1587018542605);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1092,"浙A68327",0,1587018543004);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1093,"浙A10985",1,1587018544873);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1094,"浙A98588",1,1587018546542);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1095,"浙A64901",1,1587018546974);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1096,"浙A96066",0,1587018546391);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1097,"浙A52902",0,1587018545569);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1098,"浙A51581",0,1587018545379);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1099,"浙A40320",1,1587018548027);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (2,1100,"浙A66757",0,1587018545928);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1000,"浙A75739",1,1587018550691);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1001,"浙A43363",1,1587018550298);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1002,"浙A44918",1,1587018543229);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1003,"浙A40303",0,1587018550495);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1004,"浙A54421",1,1587018549456);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1005,"浙A15503",0,1587018544079);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1006,"浙A81365",0,1587018543072);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1007,"浙A27612",1,1587018550877);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1008,"浙A36855",1,1587018547831);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1009,"浙A20823",0,1587018544529);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1010,"浙A25453",1,1587018542683);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1011,"浙A18227",0,1587018550878);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1012,"浙A90483",1,1587018548879);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1013,"浙A10884",0,1587018548611);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1014,"浙A33574",0,1587018544512);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1015,"浙A42690",0,1587018544939);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1016,"浙A16571",0,1587018543025);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1017,"浙A94376",0,1587018547291);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1018,"浙A69247",1,1587018545197);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1019,"浙A23700",0,1587018547192);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1020,"浙A97943",1,1587018543696);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1021,"浙A95594",1,1587018543427);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1022,"浙A78854",1,1587018542406);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1023,"浙A84319",1,1587018542820);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1024,"浙A86882",0,1587018542351);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1025,"浙A95535",1,1587018546131);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1026,"浙A13155",1,1587018545617);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1027,"浙A46553",1,1587018546673);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1028,"浙A74247",1,1587018542395);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1029,"浙A87477",0,1587018550858);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1030,"浙A76030",0,1587018549317);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1031,"浙A79665",1,1587018547295);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1032,"浙A48724",1,1587018546786);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1033,"浙A64115",1,1587018544849);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1034,"浙A75160",1,1587018547041);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1035,"浙A64521",0,1587018543939);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1036,"浙A56354",1,1587018545492);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1037,"浙A74544",1,1587018548707);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1038,"浙A21091",1,1587018543031);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1039,"浙A58217",0,1587018547684);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1040,"浙A15197",1,1587018546052);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1041,"浙A66482",0,1587018544606);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1042,"浙A21319",0,1587018542063);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1043,"浙A92004",0,1587018548446);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1044,"浙A28123",0,1587018549654);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1045,"浙A42167",1,1587018543037);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1046,"浙A84594",0,1587018550126);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1047,"浙A75376",0,1587018543576);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1048,"浙A98294",0,1587018546075);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1049,"浙A45403",1,1587018545057);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1050,"浙A57725",0,1587018546228);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1051,"浙A89841",0,1587018546718);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1052,"浙A46898",0,1587018543769);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1053,"浙A92095",1,1587018547748);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1054,"浙A72607",1,1587018548577);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1055,"浙A87577",1,1587018542785);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1056,"浙A41161",0,1587018546159);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1057,"浙A17552",1,1587018549920);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1058,"浙A65141",0,1587018549746);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1059,"浙A12763",0,1587018545227);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1060,"浙A92546",0,1587018546925);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1061,"浙A50588",1,1587018544192);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1062,"浙A70048",1,1587018549266);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1063,"浙A66873",0,1587018543288);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1064,"浙A20346",1,1587018550683);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1065,"浙A57227",1,1587018550175);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1066,"浙A26384",1,1587018548218);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1067,"浙A23720",0,1587018549511);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1068,"浙A64680",0,1587018546980);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1069,"浙A75484",0,1587018545935);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1070,"浙A87782",0,1587018548060);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1071,"浙A35414",1,1587018542635);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1072,"浙A85055",0,1587018544931);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1073,"浙A67333",1,1587018545812);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1074,"浙A19112",0,1587018549900);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1075,"浙A26743",0,1587018548916);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1076,"浙A16319",0,1587018547220);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1077,"浙A24490",0,1587018549408);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1078,"浙A67365",0,1587018550829);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1079,"浙A82753",1,1587018544044);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1080,"浙A42898",0,1587018542356);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1081,"浙A81189",0,1587018547799);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1082,"浙A59101",1,1587018544780);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1083,"浙A32542",1,1587018547069);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1084,"浙A14712",0,1587018548272);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1085,"浙A49511",1,1587018545051);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1086,"浙A89770",1,1587018547577);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1087,"浙A91393",1,1587018544209);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1088,"浙A94112",1,1587018547394);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1089,"浙A61211",0,1587018544704);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1090,"浙A11653",0,1587018544473);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1091,"浙A13500",0,1587018549936);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1092,"浙A32587",0,1587018542254);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1093,"浙A44323",0,1587018545202);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1094,"浙A26997",0,1587018543546);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1095,"浙A88530",1,1587018548523);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1096,"浙A18764",0,1587018545624);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1097,"浙A73015",1,1587018542137);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1098,"浙A81236",1,1587018547707);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1099,"浙A87259",0,1587018547089);
insert into parking_space(parkinglot_id,id,car_id,in_use,park_time) values (3,1100,"浙A25554",0,1587018548858);

```

引入My

执行select * from order会报错：
[42000][1064] You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'order' at line 1

原因：应该由于数据表名称order是查询的关键字之一（order by),引起歧义


### ESMAP用法
[配置地图](https://www.esmap.cn/escopemap/content/cn/develope/map-styleedit.html)


