package com.corepoc.lambdaExpressions;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<Book> getBooks(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(101,"Core Java",500));
        bookList.add(new Book(200,"C++",600));
        bookList.add(new Book(300,"Python",700));
        bookList.add(new Book(400,"Oracle",800));
        return bookList;
    }
}
