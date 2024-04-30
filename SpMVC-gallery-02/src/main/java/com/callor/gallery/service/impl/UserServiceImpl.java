package com.callor.gallery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callor.gallery.dao.RoleDao;
import com.callor.gallery.dao.UserDao;
import com.callor.gallery.models.UserVO;
import com.callor.gallery.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	private final RoleDao roleDao;
	
	// IOC 제어의역전
	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		super();
		this.userDao = userDao;
		this.roleDao = roleDao;
	}
	
	@Autowired
	@Override
	public void create_table() {
		// oracle 에서 table 생성하는 법
		try {
			userDao.create_table(null);
		} catch (Exception e) {}
		
		try {
			roleDao.create_table(null);
		} catch (Exception e) {}
	}	
	
	@Override
	public int create_user(UserVO vo) {
		return 0;
	}


}
