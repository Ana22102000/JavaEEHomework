package com.example.demo.Controllers;

import com.example.demo.Database.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ListController {

    @Autowired
    private BookRepository bookRepository;

//    @PreAuthorize("hasAuthority('VIEW_SELECTED')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("books", bookRepository.findAllBy());

        return "list";

    }

}
