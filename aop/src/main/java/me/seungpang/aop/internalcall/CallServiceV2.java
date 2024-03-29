package me.seungpang.aop.internalcall;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CallServiceV2 {

	//private final ApplicationContext applicationContext;
	private final ObjectProvider<CallServiceV2> callServiceProvider;

	public CallServiceV2(
		ObjectProvider<CallServiceV2> callServiceProvider) {
		this.callServiceProvider = callServiceProvider;
	}

	public void external() {
		log.info("call external");
		CallServiceV2 callServiceV2 = callServiceProvider.getObject();
		callServiceV2.internal(); //this.internal(); 내부 메서드 호출
	}

	public void internal() {
		log.info("call internal");
	}
}
