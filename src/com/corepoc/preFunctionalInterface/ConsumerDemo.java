package com.corepoc.preFunctionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

//always consumes input , no output is expected
public class ConsumerDemo /*implements Consumer<Integer>*/ {
    /*@Override
    public void accept(Integer t) {
        System.out.println("Printing "+t);
    }*/

    public static void main(String[] args) {
        Consumer<Integer> consumer=(t)-> {
            System.out.println("Printing "+t);
        };
        consumer.accept(30);

        //stream API has the forEach that accepts the Consumer FI
        List<Integer> integerList= Arrays.asList(1,2,3,4,5);
        integerList.stream().forEach(consumer);
        integerList.stream().forEach((i)->System.out.println("Print :" +i));
    }
}
