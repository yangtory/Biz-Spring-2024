package com.callor.gallery.service;

import org.springframework.dao.DataAccessException;

import com.callor.gallery.models.UserVO;

public interface UserService {
	
	public int create_user(UserVO vo) throws DataAccessException;
	public void create_table();
}
