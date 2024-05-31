-- galleryDB4

CREATE DATABASE galleryDB4;
USE galleryDB4;
SHOW TABLES;
DESC tbl_images;
SELECT * FROM tbl_gallerys;
SELECT * FROM tbl_images;

DROP TABLE tbl_images;
DROP TABLE tbl_gallerys;

SELECT * FROM tbl_gallerys G
LEFT JOIN tbl_images I
ON G.g_id = I.i_gid;