package com.callor.hello.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.callor.hello.models.ProductVO;

public interface ProductDao {
	
	@Select("SELECT * FROM tbl_product")
	public List<ProductVO>selectAll();

}
