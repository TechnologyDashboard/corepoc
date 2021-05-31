package com.corepoc.MapVsFaltMap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomerDAO {

    public static List<Customer> getCustomers(){
        return Stream.of(
                new Customer(101,"Bikash","Bikash@gmail.com", Arrays.asList("111111","2222222")),
                new Customer(300,"Vimal","Vimal@gmail.com", Arrays.asList("333333","444444")),
                new Customer(500,"Bikram","Bikram@gmail.com", Arrays.asList("555555","666666")),
                new Customer(700,"Krysty","Krysty@gmail.com", Arrays.asList("777777","888888")),
                new Customer(900,"Serene","Serene@gmail.com", Arrays.asList("999999","0000000"))
        ).collect(Collectors.toList());

    }
}
