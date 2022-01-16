package me.seungpang.aop.proxyvs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import me.seungpang.aop.member.MemberService;
import me.seungpang.aop.member.MemberServiceImpl;
import me.seungpang.aop.proxyvs.code.ProxyDIAspect;

@Slf4j
//@SpringBootTest(properties = {"spring.aop.proxy-target-class=false"}) //JDK 동적 프록시
@SpringBootTest(properties = {"spring.aop.proxy-target-class=true"}) //CHLIB 동적 프록시
@Import(ProxyDIAspect.class)
public class ProxyDITest {

	@Autowired
	MemberService memberService;

	@Autowired
	MemberServiceImpl memberServiceImpl;

	@Test
	void go() {
		log.info("memberService class={}", memberService.getClass());
		log.info("memberServiceImpl class={}", memberServiceImpl.getClass());
		memberServiceImpl.hello("hello");
	}
}
