-- gallery

use galleryDB2;
select * from tbl_gallerys;

drop table tbl_gallerys;
drop table tbl_images;

create table tbl_images(
i_id	VARCHAR(125)		PRIMARY KEY,
i_gid	VARCHAR(125)	NOT NULL	,
i_origin_image	VARCHAR(255)	NOT NULL	,
i_up_image	VARCHAR(255)	NOT NULL	,
CONSTRAINT FK_GALLERY
FOREIGN KEY (i_gid)
REFERENCES tbl_gallerys(g_id)
ON DELETE CASCADE
);