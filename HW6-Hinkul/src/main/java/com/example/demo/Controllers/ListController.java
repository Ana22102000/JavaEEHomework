package com.example.demo.Controllers;

import com.example.demo.Database.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ListController {

    ListController(BookService bookService) {
        this.bookService = bookService;
    }

    private BookService bookService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("books", bookService.getAllBooks());

        return "list";

    }

}
