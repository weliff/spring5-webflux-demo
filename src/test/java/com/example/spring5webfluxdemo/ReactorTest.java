package com.example.spring5webfluxdemo;

import com.example.spring5webfluxdemo.handler.ProductHandler;
import com.example.spring5webfluxdemo.router.ProductRouter;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ReactorTest {

    @Test
    public void publishOn() throws Exception {

        Flux.range(1, 3)
                .log()
                .publishOn(Schedulers.newSingle("pub map"))
                .subscribeOn(Schedulers.newSingle("sub"))
                .map(i -> {
                    printThread("Map -> " + i);
                    return i;
                })
                .publishOn(Schedulers.newSingle("pub subscribe")) //afeta onde os proximos operadores(downstream) serao executados
                .subscribeOn(Schedulers.newSingle("sub two"))//no momento de subscription,
                    // afeta toda a cadeia onde os dados serao emitidos e executados, apenas o primeiro eh considerado
                    // e nao importa onde seja colocado na cadeia
                .subscribe(i -> printThread("Sub -> "));

        CountDownLatch c = new CountDownLatch(1);
        c.await(1, TimeUnit.SECONDS);
    }

    private void printThread(String text) {
        System.out.println(text + " " + Thread.currentThread().getName());
    }
}
