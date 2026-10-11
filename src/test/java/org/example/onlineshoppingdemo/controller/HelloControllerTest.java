package org.example.onlineshoppingdemo.controller;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HelloControllerTest {
    @Mock
    GetSum mockAddService;

    @Resource
    GetSum getSum;

    private HelloController helloController = new HelloController();

    @Test
    void add() {
        helloController = new HelloController(mockAddService);
        // 设置 mock：不管输入什么参数，addService.add() 都返回 100
        when(mockAddService.add(anyInt(), anyInt())).thenReturn(100);
        int res = helloController.add(1, 3);
        assertEquals(100, res);
    }

    @Test
    void getHello() {
        assertEquals("GET Hello World", helloController.getHello());
    }

    @Test
    void postHello() {
        assertEquals("POST Hello World", helloController.postHello());
    }

    @Test
    void echo() {
        assertEquals("ECHO from: test", helloController.echo("test"));
    }

    @Test
    void echoWithEmptyString() {
        assertEquals("ECHO from: ", helloController.echo(""));
    }

    @Test
    void addWithRealGetSum() {
        helloController = new HelloController(getSum);
        assertEquals(5, helloController.add(2, 3));
    }
}