package com.corepoc.lambdaExpressions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookService {
    public static void main(String[] args) {
      /*  BookDAO bookDAO=new BookDAO();
        List<Book> newBookList=bookDAO.getBooks();
        System.out.print(newBookList);*/

        System.out.println("Approach1 "+new BookService().getBooksInSortApproach1());
        System.out.println("Approach2 "+new BookService().getBooksInSortApproach2());
        System.out.println("Approach3 "+new BookService().getBooksInSortApproach2());


    }

    //Approach1
    //sorting without lambda expression
    public List<Book> getBooksInSortApproach1(){
        List<Book> newBookList=new BookDAO().getBooks();
        Collections.sort(newBookList,new MyComparator());
        return newBookList;
    }
    //Approach2
    //sorting without lambda expression
    public List<Book> getBooksInSortApproach2(){
        List<Book> newBookList=new BookDAO().getBooks();
        Collections.sort(newBookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                //return o1.getName().compareTo(o2.getName()); //ascending order
                return o2.getName().compareTo(o1.getName()); //descending order
            }
        });
        return newBookList;
    }

    //Approach3
    //sorting with lambda expression
    public List<Book> getBooksInSortApproach3(){
        List<Book> newBookList=new BookDAO().getBooks();
        Collections.sort(newBookList, (o1, o2) -> {
            return o2.getName().compareTo(o1.getName()); //descending order
        });
        return newBookList;
    }
}

//Approach1
class MyComparator implements Comparator<Book>{

    @Override
    public int compare(Book o1, Book o2) {
        //return o1.getName().compareTo(o2.getName()); //ascending order
        return o2.getName().compareTo(o1.getName()); //descending order
    }
}

//Approach2
//no separate class for comparator
