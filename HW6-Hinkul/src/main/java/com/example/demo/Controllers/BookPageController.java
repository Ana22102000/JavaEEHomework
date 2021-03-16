package com.example.demo.Controllers;

import com.example.demo.Database.BookEntity;
import com.example.demo.Database.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookPageController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"/book/{isbn}"}, method = RequestMethod.GET)
    public String bookGet(@PathVariable(value="isbn") String isbn, Model model) {
        BookEntity bookEntity = bookService.getBookByIsbn(isbn);

        model.addAttribute("title", bookEntity.getTitle());
        model.addAttribute("author_surname", bookEntity.getAuthor().getAuthorSurname());
        model.addAttribute("author_name", bookEntity.getAuthor().getAuthorName());

        return "book";
    }
}

