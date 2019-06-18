package com.project.util;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
/**
 * 
 * @author s
 *  token工具类，用于生成token以及校验token信息
 */

public class JwtToken {
	
	public static String createToken(String username) throws Exception
	{
		Date iatDate=new Date();
		Calendar nowTime=Calendar.getInstance();
		nowTime.add(Calendar.DAY_OF_MONTH,30);
		Date expireDate=nowTime.getTime();
		Map<String, Object> map=new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		String token=JWT.create().withHeader(map).withClaim("username",username)
		.withIssuedAt(iatDate).withExpiresAt(expireDate).sign(Algorithm.HMAC256("Yun"));
		return token;
	}
	
	public static boolean checkToken(String token) throws Exception
	{
		JWTVerifier verifier=JWT.require(Algorithm.HMAC256("Yun")).build();
		try {
		verifier.verify(token);
		 return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
