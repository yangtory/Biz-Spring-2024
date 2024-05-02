-- galleryDB2

use galleryDB2;

create table tbl_users(
username	VARCHAR(125)		PRIMARY KEY,
password	VARCHAR(125)	NOT NULL	,
email	VARCHAR(125)	NOT NULL	,
tel	VARCHAR(125)	NOT NULL	
);

create table tbl_roles(
r_username	VARCHAR(125),
r_role	VARCHAR(125) NOT NULL,

CONSTRAINT PK_ROLE
PRIMARY KEY (r_username,r_role),

CONSTRAINT FK_USER
FOREIGN KEY (r_username)
REFERENCES tbl_users(username)
ON DELETE CASCADE	
);

select * from tbl_users;
select * from tbl_roles;
DESC tbl_roles;
DESC tbl_users;
drop table tbl_roles;
drop table tbl_users;

show tables;