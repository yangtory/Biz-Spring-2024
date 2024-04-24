-- gallery

USE galleryDB;
select * from tbl_gallerys;
select g_time,length(g_image) from tbl_gallerys ORDER BY g_time DESC;