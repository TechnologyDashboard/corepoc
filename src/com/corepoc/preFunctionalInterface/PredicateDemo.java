package com.corepoc.preFunctionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

//For conditional Check returns boolean
public class PredicateDemo implements Predicate<Integer> {
    @Override
    public boolean test(Integer t) {
        if(t%2==0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Predicate<Integer> predicate = new PredicateDemo();

        //traditional approach of test method overridden
        System.out.println("predicate isEven "+predicate.test(7));

        //lambda approach for test method
        Predicate<Integer> newPredicate= (i)-> {
            if(i%2==0){
                return true;
            }else{
                return false;
            }
        };
        System.out.println("newPredicate isEven "+newPredicate.test(8));

        //lambda approach for test method
        Predicate<Integer> new1Predicate= p -> p%2 == 0;
        System.out.println("new1Predicate isEven "+new1Predicate.test(8));

        //stream API has the filter that accepts the Consumer FI
        List<Integer> integerList= Arrays.asList(1,2,3,4,5);
        integerList.stream().filter(new1Predicate).forEach((i)->System.out.println("Print :" +i));
    }
}
