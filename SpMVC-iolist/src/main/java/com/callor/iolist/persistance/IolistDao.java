package com.callor.iolist.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.callor.iolist.models.IolistVO;

public interface IolistDao {


	public List<IolistVO> selectAll(); 
	public List<IolistVO> sum();
	public IolistVO findById(String io_seq);
	
	@Insert("INSERT INTO tbl_iolist(io_date, io_time, io_pname, io_input, io_price, io_quan) "
			+ " VALUES(#{io_date},#{io_time},#{io_pname}, #{io_input}, #{io_price}, #{io_quan}) ")
	public void insert(IolistVO vo);
	
	@Delete("DELETE FROM tbl_iolist WHERE io_seq=#{io_seq}")
	public void delete(String io_seq);
	
	@Update(" UPDATE tbl_iolist "
			+ " SET io_input = #{io_input}, "
			+ " io_pname = #{io_pname}, "
			+ " io_price = #{io_price}, "
			+ " io_quan = #{io_quan} "
			+ " WHERE io_seq= #{io_seq} ")
	public void update(IolistVO vo);
}
