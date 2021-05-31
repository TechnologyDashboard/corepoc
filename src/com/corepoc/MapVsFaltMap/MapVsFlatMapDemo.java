package com.corepoc.MapVsFaltMap;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapVsFlatMapDemo {

    public static void main(String[] args) {
        List<Customer> customerList=CustomerDAO.getCustomers();

        System.out.println("customerList from DAO : "+customerList);

        // get the customer email . so this is .map intermediary operation convert from Customer -> String
        // List<Customer> convert List<String>   -- > Data Transformation
        //1 to 1 mapping - one customer has 1 email ID only then use .map
        List<String> emails=customerList.stream().
                map(customer -> customer.getEmail()).collect(Collectors.toList());
        System.out.println("List of Email from : "+emails);

        //flatMap fetch phone numbers
        //if we use .map then -- > Stream of Stream non flattened of phoneNumbers from : [[111111, 2222222],
        // [333333, 444444], [555555, 666666], [777777, 888888], [999999, 0000000]]
        //use flatMap when we have 1 to many scnerios like 1 customer has many phone numbers
        List<List<String>> phoneNumbers=customerList.stream()
                .map(customer -> customer.getPhoneNumbers())
                .collect(Collectors.toList());
        System.out.println("Stream of Stream non flattened of phoneNumbers using Map from : "+phoneNumbers);

        //using flatMap mapping : customer  -> phone numbers
        List<String> phones=customerList.stream()
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .collect(Collectors.toList());
        System.out.println("Stream of flattened phoneNumbers using flatMap from : "+phones);



    }
}
