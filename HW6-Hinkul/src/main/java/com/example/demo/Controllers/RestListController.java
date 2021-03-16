package com.example.demo.Controllers;

import com.example.demo.BookModel;
import com.example.demo.Database.BookEntity;
import com.example.demo.Database.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestListController {

   @Autowired
    private BookService bookService;

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity<BookModel> formPost(@RequestBody final BookModel bookModel) {
        bookService.createBook(bookModel.getTitle(), bookModel.getIsbn(), bookModel.getAuthorSurname(), bookModel.getAuthorName());
        return ResponseEntity.ok(bookModel);
    }

    //@ResponseBody
    @RequestMapping(value = "/get_books", method = RequestMethod.GET)
    public List<BookEntity> getBooks(@RequestParam (value = "title", defaultValue = "") final String title,
                                     @RequestParam (value = "author_surname", defaultValue = "") final String surname,
                                     @RequestParam (value = "author_name", defaultValue = "") final String name) {
        return bookService.searchBooks(title,surname,name);
    }

}
