package com.corepoc.optional;

import com.corepoc.MapVsFaltMap.Customer;
import com.sun.deploy.security.ruleset.ExceptionRule;

import java.util.Arrays;
import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        Customer customer=  new Customer(101,"Bikash","Bikash@gmail.com", Arrays.asList("111111","2222222"));
        Customer customerWithNullEmail=  new Customer(101,"Bikash",null, Arrays.asList("111111","2222222"));

        //empty
        //of
        //ofNullable

        Optional<Object> emptyOptional=Optional.empty();
        System.out.println("emptyOptional "+emptyOptional);

       try{
           //does a null check , if object is null will throw NPE
           //if return will have null go for of
           Optional<String> emailOptional1=Optional.of(customerWithNullEmail.getEmail());
           System.out.println("emailOptional1 "+emailOptional1);
       }catch(Exception ex){
           ex.printStackTrace();
       }

        //if value is null will return empty optional object
        //if return may or may not be null go for ofNullable
        Optional<String> emailOptional2=Optional.ofNullable(customerWithNullEmail.getEmail());
        System.out.println("emailOptional2 "+emailOptional2);

        if(emailOptional2.isPresent()){
            System.out.println("emailOptional2  isPresent "+emailOptional2);
        }

        System.out.println("emailOptional2  isPresent "+emailOptional2.orElse("defaultEmail.com"));

        try{
            //can throw own customised exception
            System.out.println("emailOptional2  isPresent "+emailOptional2
                    .orElseThrow(() -> new IllegalArgumentException("no email")));
        }catch(Exception e){}


        Optional<String> emailOptional3=Optional.of(customer.getEmail());
        System.out.println("emailOptional3  isPresent "+emailOptional3
                .map(String::toUpperCase)
                .orElseGet(() -> "defaultYahoo.com"));
    }
}
