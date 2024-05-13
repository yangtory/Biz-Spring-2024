-- galleryDB2

use galleryDB2;
show tables;
drop table tbl_mygallery;

create table tbl_mygallery(
	g_id VARCHAR(125) PRIMARY KEY,
	g_date VARCHAR(10),
	g_time	VARCHAR(10),
	g_subject VARCHAR(125),
	g_content VARCHAR(400),
	g_writer VARCHAR(125),
	g_password VARCHAR(125),
	g_image VARCHAR(225)
);