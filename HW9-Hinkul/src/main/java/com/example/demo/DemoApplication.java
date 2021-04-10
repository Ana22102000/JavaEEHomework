package com.example.demo;

import com.example.demo.Database.Entity.AuthorEntity;
import com.example.demo.Database.Entity.BookEntity;
import com.example.demo.Database.Entity.PermissionEntity;
import com.example.demo.Database.Entity.UserEntity;
import com.example.demo.Database.Repository.AuthorRepository;
import com.example.demo.Database.Repository.BookRepository;
import com.example.demo.Database.Repository.PermissionRepository;
import com.example.demo.Database.Repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class DemoApplication {


    private static BookRepository bookRepository;
    private static AuthorRepository authorRepository;
    private static PermissionRepository permissionRepository;
    private static UserRepository userRepository;

	public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        bookRepository = applicationContext.getBean(BookRepository.class);
        authorRepository = applicationContext.getBean(AuthorRepository.class);
        permissionRepository = applicationContext.getBean(PermissionRepository.class);
        userRepository = applicationContext.getBean(UserRepository.class);
        initDatabase();
        initUsers();
        System.out.println(userRepository.findByLogin("q"));
	}

    private static void initDatabase() {

        createBook(new BookModel("Harry Potter","978-0-47-57213-7","Rowling","Joanne"));
        createBook(new BookModel("Lord of the rings","894-3-95-22425-5","Tolkien","John"));
        createBook(new BookModel("Sherlock Holmes","998-9-66-91417-1","Conan Doyle","Arthur"));
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

    private static void initUsers() {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermission(Permission.VIEW_SELECTED);
        permissionEntity = permissionRepository.save(permissionEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("q");
        userEntity.setPassword("qwertyqwerty");
        userEntity.setPermissions(List.of(permissionEntity));
        userEntity = userRepository.save(userEntity);

	}

}
