
package com.cbt.interceptor;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 增加业务日志输出
 * 
 * @ClassName: ServiceInterceptor
 * @Description: 日志记录AOP实现
 * @author luohao
 * @date 2018/04/04
 * 
 */
@Aspect
public class ServiceInterceptor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 1分钟
	private static final long ONE_MINUTE = 60000;

	@Pointcut("execution(* com.cbt.service..*.*(..))")
	public void webLog() {
	}

	// @Before("webLog()")
	// public void addBeforeLogger(JoinPoint joinPoint) {
	//
	// logger.info("execute[{}]start", joinPoint.getSignature().getName());
	// logger.info(joinPoint.getSignature().toString());
	// logger.info(parseParames(joinPoint.getArgs()));
	// }
	//
	// @AfterReturning("webLog()")
	// public void addAfterReturningLogger(JoinPoint joinPoint) {
	// logger.info("execute[{}]end", joinPoint.getSignature().getName());
	// }

	@Around("webLog()")
	public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {

		Signature signature = joinPoint.getSignature();
		String methodName = signature.getDeclaringTypeName() + "." + signature.getName();

		logger.info("execute[{}]start", joinPoint.getSignature().getName());
		logger.info(joinPoint.getSignature().toString());
		logger.info(parseParames(joinPoint.getArgs()));

		// 定义返回对象、得到方法需要的参数
		Object obj = null;
		Object[] args = joinPoint.getArgs();
		long startTime = System.currentTimeMillis();

		try {
			obj = joinPoint.proceed(args);
		} catch (Throwable e) {
			logger.error("统计某方法执行耗时环绕通知出错", e);
			throw e;
		}

		logger.info("execute[{}]end", joinPoint.getSignature().getName());

		long endTime = System.currentTimeMillis();
		// 打印耗时的信息
		this.printExecTime(methodName, startTime, endTime);

		return obj;
	}

	/**
	 * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
	 * 
	 * @param methodName
	 * @param startTime
	 * @param endTime
	 */
	private void printExecTime(String methodName, long startTime, long endTime) {
		long diffTime = endTime - startTime;
		if (diffTime > ONE_MINUTE) {
			logger.warn("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
		} else {
			logger.debug("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
		}
	}

	private String parseParames(Object[] parames) {
		if (null == parames || parames.length <= 0) {
			return "";
		}
		StringBuilder param = new StringBuilder("Parameters: ");
		for (Object obj : parames) {
			if (obj != null) {
				param.append(ToStringBuilder.reflectionToString(obj)).append(" ");
			} else {
				param.append(" NULL ");
			}
		}
		return param.toString();
	}

}
