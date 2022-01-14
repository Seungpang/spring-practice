package me.seungpang.aop.internalcall;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CallServiceV0 {

	public void external() {
		log.info("call external");
		internal(); //this.internal(); 내부 메서드 호출
	}

	public void internal() {
		log.info("call internal");
	}
}
