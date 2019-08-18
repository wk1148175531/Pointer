package com.project.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestJob {

	private static Logger logger = Logger.getLogger(TestJob.class);
	
	public void startJob()
	{
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("job start,time:"+format.format(new Date()));
//		System.out.println("job start,time:"+format.format(new Date()));
	}
}
