package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RestListController {

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ResponseEntity<BookModel> formPost(@RequestBody final BookModel bookModel) {
        System.out.println(bookModel);
        Database.getBookModelList().add(bookModel);

        return ResponseEntity.ok(bookModel);
    }

    //@ResponseBody
    @RequestMapping(value = "/get_books", method = RequestMethod.GET)
    public ArrayList<BookModel> getBooks(@RequestParam (value = "title", defaultValue = "") final String title) {
        return Database.getBookModelListSearch(title);
    }

}
