package org.example;

import java.util.Scanner;

public class BookHandler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 4; i++){
            System.out.println("Enter the Book detail..");
            System.out.println("Enter Book name");
            String name = sc.nextLine();
            System.out.println("Enter Book Author name");
            String author = sc.nextLine();

            Book book = new Book(name, author);
            BookRepo bookRepo = new BookRepo();
            bookRepo.saveBook(book);
            System.out.println("Do you want to exit?");


        }

        sc.close();


    }
}
