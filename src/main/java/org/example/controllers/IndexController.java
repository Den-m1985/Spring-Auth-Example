package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Principal principal, Model model) {
//        if (principal != null) {
//            return "redirect:/notes";
//        }
        return "index";
    }

}
