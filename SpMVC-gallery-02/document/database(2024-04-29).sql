-- galleryDB2

USE galleryDB2;
DESC tbl_images;
select * from tbl_gallerys;
select * from tbl_images;

drop table tbl_images;
drop table tbl_gallerys;

select last_insert_id() ;