package com.project.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

//token映射器
public interface JwtMapper {

	public int insertToken(@Param("username")String username,@Param("token")String token,@Param("issue_time")Date issue_time,
			@Param("expires_time")Date expires_time);
	public int findToken(String token);
	public int delExpiredTokens();
}
