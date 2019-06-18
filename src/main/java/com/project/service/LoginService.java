package com.project.service;

import java.util.Date;
import java.util.List;

import com.project.beans.LoginMsg;

public interface LoginService {

	public List<LoginMsg> getLoginList();
	public int checkLogin(String username,String password);
	public int insertToken(String username,String token,Date issue_time,Date expires_time);
	public boolean checkToken(String token);
	public int checkManagerLogin(String username,String password);
}
