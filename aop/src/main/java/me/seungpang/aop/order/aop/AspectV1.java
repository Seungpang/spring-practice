package me.seungpang.aop.order.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class AspectV1 {

	/**
	 *  execution(* me.seungpang.aop.order..*(..))는 포인트컷
	 *  doLog는 어드바이스
	 */
	@Around("execution(* me.seungpang.aop.order..*(..))")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
		return joinPoint.proceed();
	}
}
