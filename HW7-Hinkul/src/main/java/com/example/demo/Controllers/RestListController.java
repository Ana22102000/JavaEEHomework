package com.example.demo.Controllers;

import com.example.demo.BookModel;
import com.example.demo.Database.Entity.AuthorEntity;
import com.example.demo.Database.Entity.BookEntity;
import com.example.demo.Database.Repository.AuthorRepository;
import com.example.demo.Database.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestListController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity<BookModel> formPost(@RequestBody final BookModel bookModel) {
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
        return ResponseEntity.ok(bookModel);
    }

    //@ResponseBody
    @RequestMapping(value = "/get_books", method = RequestMethod.GET)
    public List<BookEntity> getBooks(@RequestParam (value = "title", defaultValue = "") final String title,
                                     @RequestParam (value = "isbn", defaultValue = "") final String isbn,
                                     @RequestParam (value = "author_surname", defaultValue = "") final String surname,
                                     @RequestParam (value = "author_name", defaultValue = "") final String name) {

        return bookRepository.findBookEntities(title, isbn, surname, name);
    }

}
