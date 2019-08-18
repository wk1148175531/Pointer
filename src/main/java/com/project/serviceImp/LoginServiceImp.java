package com.project.serviceImp;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.beans.LoginMsg;
import com.project.mapper.JwtMapper;
import com.project.mapper.LoginMapper;
import com.project.service.LoginService;

@Service
public class LoginServiceImp implements LoginService{

	@Autowired
	LoginMapper mapper;
	@Autowired
	JwtMapper jwtMapper;
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public List<LoginMsg> getLoginList() {
		
		return mapper.getLoginList();
	}
    
	//检查app登录
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public int checkLogin(String username,String password) {
		
		return mapper.checkLogin(username,password);
	}
    //插入token信息入数据库
	@Override
	public int insertToken(String username, String token, Date issue_time, Date expires_time) {
		
		return jwtMapper.insertToken(username, token, issue_time, expires_time);
	}

	@Override
	public boolean checkToken(String token) {
		
		return false;
	}
    //检查后台登录
	@Override
	public int checkManagerLogin(String username, String password) {
		
		return mapper.checkManagerLogin(username, password);
	}

}
