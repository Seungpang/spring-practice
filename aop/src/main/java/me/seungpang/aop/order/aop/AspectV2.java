package me.seungpang.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV2 {

	//me.seungpang.order 패키지와 하위 패키지
	@Pointcut("execution(* me.seungpang.aop.order..*(..))")
	private void allOrder() {} //pointcut signature

	/**
	 *  execution(* me.seungpang.aop.order..*(..))는 포인트컷
	 *  doLog는 어드바이스
	 */
	@Around("allOrder()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
		return joinPoint.proceed();
	}
}
