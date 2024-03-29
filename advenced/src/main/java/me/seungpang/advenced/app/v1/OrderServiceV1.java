package me.seungpang.advenced.app.v1;

import lombok.RequiredArgsConstructor;
import me.seungpang.advenced.trace.TraceStatus;
import me.seungpang.advenced.trace.hellotrace.HelloTraceV1;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;//예외를 꼭 다시 던져주어야 한다.
        }
    }
}
