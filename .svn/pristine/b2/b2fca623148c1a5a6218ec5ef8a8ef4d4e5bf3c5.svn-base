package com.cbt.interceptor;  
  
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
  
/** 
 * Add 增加业务日志输出
* @ClassName: ControlInterceptor  
* @Description: 日志记录AOP实现  
* @author polo 
* @date 2018/04/04 
* 
 */  
@Aspect  
public class ControlInterceptor {  
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  
  
    @Pointcut("execution(* com.cbt.controller..*.*(..))")
	public void webLog() {
	}
	
	@Before("webLog()")  
	public void addBeforeLogger(JoinPoint joinPoint) {
		
		logger.info("执行[{}]开始",joinPoint.getSignature().getName());
		logger.info(joinPoint.getSignature().toString());
		logger.info(parseParames(joinPoint.getArgs()));
	}

	@AfterReturning("webLog()")  
	public void addAfterReturningLogger(JoinPoint joinPoint) {
		logger.info("执行[{}]结束",joinPoint.getSignature().getName());
	}
	

	private String parseParames(Object[] parames) {
		if (null == parames || parames.length <= 0) {
			return "";
		}
		StringBuffer param = new StringBuffer("传入参数[{}] ");
		for (Object obj : parames) {
			if(obj != null){
				param.append(ToStringBuilder.reflectionToString(obj)).append(" ");
			}
		}
		return param.toString();
	}
}  