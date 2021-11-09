package me.seungpang.advenced.app.v5;

import lombok.RequiredArgsConstructor;
import me.seungpang.advenced.trace.callback.TraceCallback;
import me.seungpang.advenced.trace.callback.TraceTemplate;
import me.seungpang.advenced.trace.logtrace.LogTrace;
import me.seungpang.advenced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository,
        LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {

        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
