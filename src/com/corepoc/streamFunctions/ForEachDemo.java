package com.corepoc.streamFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ForEachDemo {


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Murrit");
        list.add("John");
        list.add("Peter");
        list.add("Marrek");
        list.add("Mac");

        for (String s:list){
            System.out.println("Traditional "+s);
        }

        //Consumer<String> cons= t->System.out.println("Lambda "+t);

        list.stream().forEach(t->System.out.println("Lambda "+t));

        //Example 2
        Map<Integer,String> map= new HashMap<>();
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        map.put(4,"d");
        map.put(5,"e");

        map.forEach((key,value)-> {
            System.out.println("inside lambda expr "+key+"_"+value);
        });//does not use the pipeline method in stream

        map.entrySet().stream().forEach(obj -> System.out.println("Lambda Map "+obj));



        //internally what forEach is doing
        Consumer<String> consumer=(t) -> System.out.println("Lambda Expr "+t);
        for(String s:list) {
            consumer.accept(s);
        }

    }
}
