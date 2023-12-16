package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux()
                .subscribe(
                        System.out::println,                                        // consumes the item
                        err -> System.out.println("error: " + err.getMessage()),    // consumes the event error (terminal event)
                        () -> System.out.println("Completed")                       // consumes the completion event (terminal event)
                );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MyBaseSubscriber<>());
        // posso usar essa tecnica qdo preciso de backpressure, ou seja, qdo preciso de um "respiro" para processar os elementos sendo enviados

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MyBaseSubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribe happened");
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        System.out.println(value.toString() + " received");
        request(1);
    }

}