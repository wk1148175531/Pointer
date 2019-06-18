package com.project.controller;

import java.io.IOException;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bouncycastle.crypto.tls.SignatureAlgorithm;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.beans.LoginMsg;
import com.project.service.LoginService;
import com.project.util.JwtToken;

import net.sf.json.JSONObject;

@Controller
public class LoginController {

	@Autowired
	LoginService service;
	
	
	//app登录接口
	@RequestMapping(value="/api/login",produces="application/json")
	@ResponseBody
	public String login(HttpServletRequest request) throws Exception
	{
        String username=request.getParameter("username");
        String password=request.getParameter("password");
		int count=service.checkLogin(username,password);
		JSONObject obj=new JSONObject();
		if(count!=0)
		{
			obj.put("status", "1");
			//给app发送接口访问token
			String token=JwtToken.createToken(username);
			Date issue_time=new Date();
			Calendar nowTime=Calendar.getInstance();
			nowTime.add(Calendar.DAY_OF_MONTH,30);
			Date expires_time=nowTime.getTime();
			service.insertToken(username, token, issue_time, expires_time);
			obj.put("token", token);
		}
		else
		{
			obj.put("status", "0");
		}
		
		return obj.toString();
	}
	
	//后台登录接口
	@RequestMapping("/loginCheck")
	@ResponseBody
	public String loginCheck(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		int checkflag=service.checkManagerLogin(username, password);
		if(checkflag!=0)
		{
//			response.sendRedirect("/Yun/manager");
//			request.getRequestDispatcher("/manager").forward(request, response);
			HttpSession session =request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			return "1";
		}
		else
		{
			return "0";
		}	
	}
}
