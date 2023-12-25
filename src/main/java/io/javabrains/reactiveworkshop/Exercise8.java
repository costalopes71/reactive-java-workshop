package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens

        // Metodo 1: fazer algo (side effect) e lançar o erro para os proximos operatos (talvez eu queira logar ou mandar metrica antes de lancar o erro)
        ReactiveSources.intNumbersFluxWithException()
                .log()
                .doOnError(e -> System.out.println("Error: " + e.getMessage()))
                .subscribe();

        // Metodo 2: tratar o erro e nao lançar:
        ReactiveSources.intNumbersFluxWithException()
                .subscribe(
                        System.out::println,
                        n -> System.out.println("Error happened")
                );

        // Print values from intNumbersFluxWithException and continue on errors

        // Esse método é útil qdo quero fazer um fallback por exemplo
        ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue((e, number) -> System.out.println("fallback being executed for number: " + number))
                .subscribe(number -> System.out.println(number));


        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2

        // Nesse método, eu posso no fallback devolver um novo Flux, que sera o Flux utilizado para continuar
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume(e -> Flux.just(-1, -2))
                .subscribe(number -> System.out.println(number));

        // Existe tbm um metodo para realizar algo qdo o flux termina, mto parecido com um block finally, nesse bloco podemos inspecionar como o Flux terminou (signal type: error, on complete etc)
        ReactiveSources.intNumbersFluxWithException()
                .log()
                .doFinally(signal -> {
                    switch (signal) {
                        case CANCEL -> System.out.println("Terminated with Canceled");
                        case ON_ERROR -> System.out.println("Terminated with error");
                        case ON_COMPLETE -> System.out.println("Terminated successfully");
                    }
                })
                .subscribe();

        System.out.println("Press a key to end");
        System.in.read();
    }

}
