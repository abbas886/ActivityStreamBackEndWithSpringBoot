package com.stackroute.activity.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActivityStreamLog {

	private static final Logger logger = LoggerFactory.getLogger(ActivityStreamLog.class);

	@Before("execution(* com.stackroute.activity.dao.validate(..))")
	public void logBefore(JoinPoint joinPoint) {

		logger.info("============@Before==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("*********************************");

	}

	@After("execution(* com.stackroute.activity.dao.validate(..))")
	public void logAfter(JoinPoint joinPoint) {

		logger.info("============@After==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("*********************************");

	}

	@AfterReturning(pointcut = "execution(* com.stackroute.activity.dao.validate(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {

		logger.debug("============@AfterReturning==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("*********************************");

	}

	@AfterThrowing(pointcut = "execution(* com.stackroute.activity.dao.validate(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		logger.info("============@AfterThrowing==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));
		logger.debug("Exception : " + error);
		logger.debug("*********************************");
	}

	@Around("execution(* com.stackroute.activity.dao.validate(..))")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		logger.info("============@Around==========");
		logger.debug("Method Name : " + joinPoint.getSignature().getName());
		logger.debug("Method arguments : " + Arrays.toString(joinPoint.getArgs()));

		logger.debug("*********************************");

		logger.debug("============@Around Before ==========");
		joinPoint.proceed();
		logger.debug("============@Around After ==========");

		logger.debug("*********************************");

	}

}
