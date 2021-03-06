package com.example.demo;

import com.example.demo.Database.Entity.AuthorEntity;
import com.example.demo.Database.Entity.BookEntity;
import com.example.demo.Database.Repository.AuthorRepository;
import com.example.demo.Database.Repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {


    private static BookRepository bookRepository;

    private static AuthorRepository authorRepository;

	public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        bookRepository = applicationContext.getBean(BookRepository.class);
        authorRepository = applicationContext.getBean(AuthorRepository.class);
        initDatabase();
	}

    private static void initDatabase() {

        createBook(new BookModel("Harry Potter","978-0-2547-5721-6","Rowling","Joanne"));
        createBook(new BookModel("Lord of the rings","894-3-9825-2425-1","Tolkien","John"));
        createBook(new BookModel("Sherlock Holmes","998-9-3766-9117-4","Conan Doyle","Arthur"));
    }

    public static boolean createBook(BookModel bookModel){
        AuthorEntity newAuthor = new AuthorEntity();
        newAuthor.setAuthorSurname(bookModel.getAuthorSurname());
        newAuthor.setAuthorName(bookModel.getAuthorName());
        newAuthor = authorRepository.save(newAuthor);

        BookEntity newBook = new BookEntity();
        newBook.setTitle(bookModel.getTitle());
        newBook.setIsbn(bookModel.getIsbn());
        newBook.setAuthorId(newAuthor.getId());
        newBook = bookRepository.save(newBook);
        System.out.println(newBook);
        System.out.println(newAuthor);
        return true;
    }

}
