package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.beans.LoginMsg;


//登录映射器
public interface LoginMapper {
	public List<LoginMsg> getLoginList();
    public int checkLogin(@Param("username")String username,@Param("password")String password);
    public int checkManagerLogin(@Param("username")String username,@Param("password")String password);
    
}
