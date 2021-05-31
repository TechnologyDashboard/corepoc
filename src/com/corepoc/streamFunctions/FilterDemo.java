package com.corepoc.streamFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FilterDemo {

    //filter  method ----> conditional check

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Murrit");
        list.add("John");
        list.add("Peter");
        list.add("Marrek");
        list.add("Mac");

        for (String s:list){
            //find the list of string that starts with M
            if(s.startsWith("M")){
                System.out.println("Traditional "+s);

            }
        }

        //Predicate<String> predicate= t->t.startsWith("M");
        //filter uses the predicate
        list.stream().filter(t->t.startsWith("M")).forEach(t->System.out.println("Lambda "+t));

        //Example 2
        Map<Integer,String> map= new HashMap<>();
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.put(4,"d");
        map.put(5,"e");

       /* map.forEach((key,value)-> {
            System.out.println("inside lambda expr "+key+"_"+value);
        });//does not use the pipeline method in stream*/

        //gets the values of the elements that are divisible by 2
        map.entrySet().stream().filter(k -> k.getKey()%2==0 ).forEach(obj -> System.out.println("Lambda Map "+obj));



        /*//internally what forEach is doing
        Consumer<String> consumer=(t) -> System.out.println("Lambda Expr "+t);
        for(String s:list) {
            consumer.accept(s);
        }*/

    }
}
