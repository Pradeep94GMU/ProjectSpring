package org.example;

public class BookValidator {

    public BookValidator(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    BookRepo bookRepo = new BookRepo();
    public String addBook(Book book){
        if(bookRepo.findBook(book.getName(), book.getAuthor())){

             return "Already book exist";
        }
        else{
            bookRepo.saveBook(book);

            return "new book added";
        }
    }
}
