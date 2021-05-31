package com.corepoc.streamAPI.realLife;

import java.util.List;
import java.util.stream.Collectors;

public class TaxService {

    public static void main(String[] args) {
        System.out.println("evaluateTaxableUsers from main " + evaluateTaxableUsers());

    }

    public static List<Employee> evaluateTaxableUsers() {

        //find the users above 5000 to pay taxes
        EmployeeDAO.getEmployees().stream().filter(employee -> employee.getSalary() > 5000)
                .forEach(t -> System.out.println("Employee from forEach " + t.getName()));
        //.collect(Collectors.toList());

        return EmployeeDAO.getEmployees().stream().filter(employee -> employee.getSalary() > 5000)
                .collect(Collectors.toList());

    }
}
