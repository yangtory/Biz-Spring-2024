package com.callor.hello.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.callor.hello.models.CustomVO;

public interface CustomDao {
	
	@Select("SELECT * FROM tbl_customer "
			+ "ORDER BY c_code DESC")
	public List<CustomVO> selectAll(); 
	
	// vo 값들을 찾아서 알아서 셋팅해줌
	public int insert(CustomVO vo);

}
