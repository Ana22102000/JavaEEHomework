package com.example.demo.Controllers;

import com.example.demo.Database.Entity.BookEntity;
import com.example.demo.Database.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookPageController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = {"/book/{isbn}"}, method = RequestMethod.GET)
    public String bookGet(@PathVariable(value="isbn") String isbn, Model model) {
        BookEntity bookEntity = bookRepository.getBookEntityByIsbn(isbn);

        model.addAttribute("title", bookEntity.getTitle());
        model.addAttribute("author_surname", bookEntity.getAuthor().getAuthorSurname());
        model.addAttribute("author_name", bookEntity.getAuthor().getAuthorName());

        return "book";
    }
}

