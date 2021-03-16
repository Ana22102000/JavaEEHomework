package com.example.demo;

import com.example.demo.Database.BookEntity;
import com.example.demo.Database.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {


    private static BookService bookService;

	public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        bookService = applicationContext.getBean(BookService.class);
        initDatabase();
	}

    private static void initDatabase() {
        bookService.createBook("Harry Potter","978-0-2547-5721-6","Rowling","Joanne");
        bookService.createBook("Lord of the rings","894-3-9825-2425-1","Tolkien","John");
        bookService.createBook("Sherlock Holmes","998-9-3766-9117-4","Conan Doyle","Arthur");
    }

}
