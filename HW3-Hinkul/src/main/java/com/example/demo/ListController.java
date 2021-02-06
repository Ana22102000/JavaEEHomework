package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ListController {


//    @RequestMapping(value = "/form", method = RequestMethod.GET)
//    public String formControllerGet() {
//        return "form-controller-get";
//    }
//
//    @RequestMapping(value = "/form", method = RequestMethod.POST)
//    public String formControllerPost(@ModelAttribute FormModel formModel, Model model) {
//        model.addAttribute("formModel", formModel);
//        return "form-controller-post";
//    }

    static ArrayList<BookModel> arrayList= new ArrayList();

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("books", Database.getBookModelList());

        return "list";

    }

}
