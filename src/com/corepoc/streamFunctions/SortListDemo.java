package com.corepoc.streamFunctions;

import com.corepoc.streamAPI.realLife.Employee;
import com.corepoc.streamAPI.realLife.EmployeeDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortListDemo {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(12);
        list.add(4);
        list.add(3);
        list.add(8);
        list.add(0);

        Collections.sort(list);//Ascending
        Collections.reverse(list);//Descending
        System.out.println("Traditional "+list);

        list.stream().sorted().forEach(t-> System.out.println("Lambda "+t));

        //using Comparator
        list.stream().sorted(Comparator.reverseOrder())
                .peek(e-> System.out.println("Selected value "+e))
                .forEach(t-> System.out.println("Lambda reverseOrder "+t));

        //compareTo function in Integer implements Comparable Interface
        list.stream().sorted((o1, o2) ->
            o2.compareTo(o1)
        ).forEach(t-> System.out.println("Lambda using comparable "+t));


        //sorting using the employee salary - Traditional
        List<Employee> employeeList = EmployeeDAO.getEmployees();
        System.out.println("Traditional employeeList before "+employeeList);
        Collections.sort(employeeList,new MyComparator());
        System.out.println("Traditional employeeList after "+employeeList);

        //Traditional using comparator
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int)(o2.getSalary()-o1.getSalary());
            }
        });
        System.out.println("Traditional employeeList reverse after "+employeeList);

        //using Lambda using Comparator FI
        Collections.sort(employeeList,(o1, o2) -> (int) (o1.getSalary()-o2.getSalary()));
        System.out.println("Lambda employeeList after Comparator FI "+employeeList);

        //Stream API + lambda
        employeeList.stream().sorted((o1, o2) -> (int) (o1.getSalary()-o2.getSalary()))
                .forEach(employee -> System.out.println("Employee Stream API + lambda sort : "+employee));

        //Stream API + lambda + method reference
        employeeList.stream().sorted((o1, o2) -> (int) (o1.getSalary()-o2.getSalary()))
                .forEach(System.out::println);

        //Stream API + lambda  + method reference
        employeeList.stream().sorted(Comparator.comparing(employee -> employee.getSalary()))
                .forEach(System.out::println);

        //Stream API + lambda (converted to method reference) + method reference
        employeeList.stream().sorted(Comparator.comparing(Employee::getSalary))
                .peek(employee -> System.out.println("Peeking employee "+employee.getName()))
                .forEach(employee -> System.out.println("Employee Stream API + lambda + method ref sort : "+employee));

    }
}

class MyComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        return (int) (o1.getSalary()- o2.getSalary());
    }
}
