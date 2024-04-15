package com.callor.hello.dao;

import java.util.List;

import com.callor.hello.models.RoleVO;

public interface RoleDao {
	
	public int insertAll(List<RoleVO> roles);
	// 한사람이 가지고 있는 권한의 리스트
	public List<RoleVO>findByUserName(String username);

}
