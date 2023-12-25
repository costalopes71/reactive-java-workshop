package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise9 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
        ReactiveSources.intNumbersFlux()
                .count()
                .subscribe(count -> System.out.println(count));

        // Collect all items of intNumbersFlux into a single list and print it
        ReactiveSources.intNumbersFlux()
                .collectList()
                .subscribe(list -> System.out.println(list));

        // Transform to a sequence of sums of adjacent two numbers
        ReactiveSources.intNumbersFlux()
                .buffer(2)
                .map(list -> list.get(0) + list.get(1))
                .subscribe(sum -> System.out.println(sum));

        System.out.println("Press a key to end");
        System.in.read();
    }

}
