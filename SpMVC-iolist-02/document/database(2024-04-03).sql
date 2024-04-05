-- iolist 
USE iolistDB2;
DESC tbl_iolist;

INSERT INTO tbl_iollist
(io_seq,io_date,io_time,io_input,io_pname,io_price,io_quan,io_total)
VALUES(#{io_seq},#{io_date},#{io_time},#{io_input},#{io_pname},#{io_price},#{io_quan},#{io_total});