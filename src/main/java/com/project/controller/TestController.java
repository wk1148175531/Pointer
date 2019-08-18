package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.beans.MovieMsg;
import com.project.service.TestService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class TestController {

	@Autowired
	TestService service;
	@Autowired
	StringRedisTemplate redisTemplate;
	
	@RequestMapping("/test")
	public String test()
	{
		return "test";
	}
	
	@RequestMapping(value="/getJson",produces="text/html;charset=utf-8")
	@ResponseBody
	public String getJson(HttpServletRequest request)
	{
		String offset = request.getParameter("offset");
		String limit = request.getParameter("limit");
//		redisTemplate.opsForValue().set("username", "123");
//		redisTemplate.opsForValue().set("password", "123");
		List<MovieMsg> msgs=service.getMsg();
		JSONObject obj=new JSONObject();
		JSONArray array=new JSONArray();
		for(MovieMsg msg:msgs)
		{
			JSONObject ob=new JSONObject();
			ob.put("videoname", msg.getVideoName());
			ob.put("url", msg.getUrl());
			ob.put("desc", msg.getDescript());
			array.add(ob);
		}
		obj.put("data", array);
//		String name = redisTemplate.opsForValue().get("username");
//		String password = redisTemplate.opsForValue().get("password");
//		System.out.println("username:"+name+",password:"+password);
		return obj.toString();
	}
}
