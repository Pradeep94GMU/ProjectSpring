package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class BookValidatorTest {

    BookValidator bookValidator;
    BookRepo bookRepo;


    @BeforeEach
    public void setUp(){
        bookRepo = Mockito.mock(BookRepo.class); //this is where we need to modify
        bookValidator = new BookValidator(bookRepo);
    }

    @Test
    public void aaddBook(){
        Book book = new Book("OS", "Feily");

        //check the mock obj that if book are exist or not
        when(bookRepo.findBook("OS", "feily")).thenReturn(false);

        String res = bookValidator.addBook(book);


        verify(bookRepo, times(1)).saveBook(book); // Verify that the save method was called on the mock


        assertEquals("new book added",res);
    }

    @Test
    public void addDuplicateBook(){
        Book book = new Book("OS", "Feily");
        bookValidator.addBook(book);
        String res = bookValidator.addBook(book);
        assertEquals("Already book exist",res);
    }
}
