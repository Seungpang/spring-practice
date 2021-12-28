package me.seungpang.aop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;
import me.seungpang.aop.order.OrderRepository;
import me.seungpang.aop.order.OrderService;
import me.seungpang.aop.order.aop.AspectV1;
import me.seungpang.aop.order.aop.AspectV2;
import me.seungpang.aop.order.aop.AspectV3;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class)
//@Import(AspectV2.class)
@Import(AspectV3.class)
public class AopTest {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Test
	void aopInfo() {
		log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
		log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
	}

	@Test
	void success() {
		orderService.orderItem("itemA");
	}

	@Test
	void exception() {
		Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
