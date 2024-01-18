package org.lesson.springlamiapizzeria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/Ingredients")
public class IngredienteController {
    @GetMapping
    public String index() {
        return "ingrediente/index";
    }
}
