package org.example;

import java.util.ArrayList;
import java.util.List;

public class BookRepo {

    List<Book> booklist = new ArrayList<>();

    //save the book
    public void saveBook(Book book){
        booklist.add(book);
        return;
    }

    //search and check if exist or not
    public boolean findBook(String name, String author){

        return booklist.stream().anyMatch(book -> book.getName().equalsIgnoreCase(name) && book.getAuthor().equalsIgnoreCase(author) );
    }


}
