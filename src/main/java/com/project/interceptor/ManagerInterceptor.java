package com.project.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.project.beans.LoginMsg;
/**
 * 
 * @author s
 *  后台登录拦截器，拦截未登录的用户直接进入后台
 */

public class ManagerInterceptor implements HandlerInterceptor{
	
//	@Resource(name="redisTemplate")
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		boolean flag=false;
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		String password=(String) session.getAttribute("password");
//		System.out.println(Susername+","+password);
		Cookie [] cookies=request.getCookies();
		for(Cookie cookie : cookies)
		{
			if("JSESSIONID".equals(cookie.getName()))
			{
				String sessionID=cookie.getValue();
				System.out.println("cookie sessionID:"+sessionID);
				try{
					LoginMsg msg=(LoginMsg)redisTemplate.opsForValue().get(sessionID);
					if(msg!=null)
					{
					
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		if(!flag)
		{
			String path=request.getServletContext().getContextPath();
	        response.sendRedirect(path+"/login");
	        return true;
		}
//		if(username==null)
//		{
//			  String path=request.getServletContext().getContextPath();
//	          response.sendRedirect(path+"/login");
//	          return true;
//		}
		return true;
	}

	
}
