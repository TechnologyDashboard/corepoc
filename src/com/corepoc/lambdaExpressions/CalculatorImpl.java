package com.corepoc.lambdaExpressions;

@FunctionalInterface
interface Calculator {
    //Step1
    //void switchOn();

    //Step2
    /*void sum(int i);*/

    //Step3
    //int subtract(int i1, int i2);

    //Step3
    int subtract(int i1, int i2);
}

public class CalculatorImpl {

    public static void main(String[] args) {
        //Step1
        //Calculator calculator = () -> System.out.println("Switch ON");
        //calculator.switchOn();

        //Step2
        /*Calculator calculator=(i) -> System.out.println("SUM is : "+i);
        calculator.sum(8);*/

        //Step3
       /* Calculator calculator=(i1,i2)-> {
            return i2-i1;
        };
        System.out.println(calculator.subtract(12,20));*/

        //Step4
    /*    Calculator calculator=(i1,i2)-> i2-i1;
        System.out.println(calculator.subtract(12,20));*/

        //Step5
        Calculator calculator=(i1,i2)-> {
            if (i2<i1) {
                throw new RuntimeException("message");
            }else {
                return i2-i1;}
        };
        System.out.println(calculator.subtract(12,20));


    }

    //() -> {}  //structure of a lambda expr
}
