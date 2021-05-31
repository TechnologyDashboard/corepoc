package com.corepoc.preFunctionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

//return some dummy data without any input, so some operation like API call and send OK back
public class SupplierDemo /*implements Supplier<String> */{
  /*  @Override
    public String get() {
        return "Hello from Override";
    }*/

    public static void main(String[] args) {
      /*  Supplier<String> stringSupplier=new SupplierDemo();
        System.out.println("Supplier "+ stringSupplier.get());
*/
        Supplier<String> supplier= ()-> {
            return "Hello from Override";
        };
        System.out.println("Lambda Supplier "+ supplier.get());

        //stream API has the filter if we are not getting any response and we want to return some dummy data
        List<String> stringList= Arrays.asList();
        System.out.println("stringList "+stringList.stream().findAny().orElseGet(supplier));

        System.out.println("stringList "+stringList.stream().findAny().orElseGet(()-> {
            return "Hello from Override stringList";
        }));
    }
}
