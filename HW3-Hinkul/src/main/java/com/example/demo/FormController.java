package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

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

    @RequestMapping(value = {"/form"}, method = RequestMethod.GET)
    public String formGet() {
        return "form";
    }

//    @RequestParam("page") int page,
//    Model model
//    ) {
//        model.addAttribute("myPageParam", page);
//        model.addAttribute("myIdParam", id);
//        return "my-first-template-page";
//    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String formPost(@ModelAttribute BookModel bookModel, Model model) {
        Database.getBookModelList().add(bookModel);

        return "redirect:/list";
    }

}
