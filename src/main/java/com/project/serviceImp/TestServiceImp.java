package com.project.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.beans.MovieMsg;
import com.project.mapper.TestMapper;
import com.project.service.TestService;

@Service
public class TestServiceImp implements TestService{

	@Autowired
	TestMapper mapper;

	@Override
	@Transactional
	public List<MovieMsg> getMsg() {
		return mapper.getMsg();
	}

}
