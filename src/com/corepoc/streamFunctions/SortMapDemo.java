package com.corepoc.streamFunctions;

import com.corepoc.streamAPI.realLife.Employee;
import sun.security.krb5.SCDynamicStoreConfig;

import java.util.*;

public class SortMapDemo {

    public static void main(String[] args) {
        Map<String,Integer> map= new HashMap<>();
        map.put("eight",8);
        map.put("four",4);
        map.put("ten",10);
        map.put("two",2);
        map.put("one",1);
        System.out.println("map before sorting "+map);

        //convert Map into List
        List<Map.Entry<String,Integer>> entryList=new ArrayList<>(map.entrySet());
        System.out.println("entryList as List before sorting "+entryList);

        //sort by Key
        Collections.sort(entryList,(o1, o2) -> {
            return o1.getKey().compareTo(o2.getKey());
        });
        System.out.println("entryList after sorting by Key List "+entryList);

        //sort by Value Lambda Expression
        Collections.sort(entryList,(o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        System.out.println("entryList after sorting by Value List "+entryList);

        //Traditional Sorting using Comparator
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        System.out.println("entryList after sorting by Key Traditional way "+entryList);

        //java8 + Map.Entry + Comparator comparingByKey + Lambda
        map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(obj -> System.out.println("Map.Entry + Comparator comparingByKey + Lambda : "+obj));

       map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(obj -> System.out.println("Map.Entry + Comparator comparingByValue + Lambda : "+obj));

       //Employee Map
        Map<Employee,Integer> employeeMap1= new HashMap<>();// if it is a HashMap it does not care about sorting

        //if we declare a TreeMap , we have to provide the Comparator logic for sorting
        Map<Employee,Integer> employeeMap= new TreeMap<>(new Comparator<Employee>() {
            /*@Override
            // Map<Integer,Employee> employeeMap= new TreeMap<>(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }*/
            @Override
            public int compare(Employee o1, Employee o2) {
                return (int)(o1.getSalary()-o2.getSalary());
            }
        });
        employeeMap.put(new Employee(101,"Roshan","IT",6000),60);
        employeeMap.put(new Employee(200,"Bikash","Civil",9000),90);
        employeeMap.put(new Employee(300,"Bimal","DEFENCE",5000),50);
        employeeMap.put(new Employee(400,"Prakash","CORE",4000),40);
        employeeMap.put(new Employee(500,"Sourav","SOCIAL",12000),120);
        System.out.println("employeeMap : "+employeeMap);

        //use stream API sorting
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((o1, o2) -> (int)(o1.getSalary()-o2.getSalary())))
                .forEach(obj -> System.out.println("Stream Sorting a Map "+obj));

        //use stream API sorting
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Employee::getSalary).reversed()))
                .forEach(obj -> System.out.println("Stream Reverse Sorting a Map "+obj));

    }
}
