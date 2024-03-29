package com.callor.hello.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.callor.hello.models.ProductVO;

public interface ProductDao {
	
	@Select("SELECT * FROM tbl_product ORDER BY p_code DESC")
	public List<ProductVO>selectAll();

	public void insert(ProductVO vo);

	@Select("SELECT * FROM tbl_product WHERE p_code= #{pCode}")
	public ProductVO findById(String pCode);

	public int update(ProductVO productVO);

	@Delete("DELETE FROM tbl_product WHERE p_code=#{pCode}")
	public int delete(String pCode);

}
