create table tbl_message(
mno int not null auto_increment,
targetid varchar(50) not null,
sender varchar(50) not null,
message text not null,
opendate timestamp,
senddate timestamp not null default now(),
primary key(mno)
);

create table tbl_user(
uid varchar(50) not null,
upw varchar(50) not null,
uname varchar(100) not null,
upoint int DEFAULT 0 not null,
primary key(uid)

);
drop table tbl_message;
drop table tbl_user;

select * from tbl_user;
select * from tbl_message;

alter table tbl_message add CONSTRAINT fk_usertarget
FOREIGN KEY (targetid) REFERENCES tbl_user(uid); 

alter table tbl_message ADD CONSTRAINT fk_usersender
FOREIGN KEY (sender) REFERENCES tbl_user(uid);

insert into tbl_user(uid,upw,uname) values('user00','user00','q');
insert into tbl_user(uid,upw,uname) values('user01','user01','w');
insert into tbl_user(uid,upw,uname) values('user02','user02','e');
insert into tbl_user(uid,upw,uname) values('user03','user03','r');
insert into tbl_user(uid,upw,uname) values('user04','user04','t');