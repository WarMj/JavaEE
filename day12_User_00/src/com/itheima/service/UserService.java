package com.itheima.service;

import com.itheima.domain.User;

public interface UserService {
	/**
	 * 添加用户信息
	 * @param user
	 * @throws Exception
	 */
	public void register(User user) throws Exception;
	
	/**
	 * 根据用户名和密码查询用户
	 * @param user
	 * @return
	 */
	public User login(User user);
}
