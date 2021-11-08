package me.seungpang.advenced.trace.threadlocal;

import lombok.extern.slf4j.Slf4j;
import me.seungpang.advenced.trace.threadlocal.code.FieldService;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        //sleep(2000); //동시성 문제 발생X
        sleep(100); //동시성 문제 발생O
        threadB.start();

        //CountDownLatch방법이 있는데 복잡해서 일단 패스
        sleep(2000); //메인 쓰레드 종료 대기

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
