package com.corepoc.streamAPI.realLife;

import com.corepoc.lambdaExpressions.Book;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public static List<Employee> getEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(101,"Roshan","IT",6000));
        employeeList.add(new Employee(200,"Bikash","Civil",9000));
        employeeList.add(new Employee(300,"Bimal","DEFENCE",5000));
        employeeList.add(new Employee(400,"Prakash","CORE",4000));
        employeeList.add(new Employee(500,"Sourav","SOCIAL",12000));
        return employeeList;
    }
}
