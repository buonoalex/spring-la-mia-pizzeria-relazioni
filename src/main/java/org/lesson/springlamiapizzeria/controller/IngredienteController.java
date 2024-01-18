package org.lesson.springlamiapizzeria.controller;

import org.lesson.springlamiapizzeria.model.Ingrediente;
import org.lesson.springlamiapizzeria.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home/Ingredients")
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public String index(Model model) {
        List<Ingrediente> ingredienteList = ingredienteRepository.findAll();
        model.addAttribute("ingredienteList", ingredienteList);
        return "ingrediente/index";
    }
}
