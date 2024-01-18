package org.lesson.springlamiapizzeria.controller;

import jakarta.validation.Valid;
import org.lesson.springlamiapizzeria.model.Ingrediente;
import org.lesson.springlamiapizzeria.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/create")
    public String addIngredient(Model model) {
        Ingrediente ingrediente = new Ingrediente();
        model.addAttribute("ingrediente", ingrediente);
        return "ingrediente/create";
    }

    @PostMapping("/create")
    public String saveIngredient(@Valid @ModelAttribute("ingrediente") Ingrediente userIng, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingrediente/create";
        } else {
            Ingrediente saveIngredient = ingredienteRepository.save(userIng);
            return "redirect:/home/Ingredients";
        }
    }

    @GetMapping("/edit/{id}")
    public String editIngredient(@PathVariable int id, Model model) {
        Optional<Ingrediente> ingredienteRecovery = ingredienteRepository.findById(id);
        Ingrediente ingrediente = ingredienteRecovery.get();
        model.addAttribute("ingrediente", ingrediente);
        return "ingrediente/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditIngredient(@PathVariable int id, @Valid @ModelAttribute("ingrediente") Ingrediente editIng, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Ingrediente/edit";
        } else {
            Ingrediente saveIngredient = ingredienteRepository.save(editIng);
            return "redirect:/home/Ingredients";
        }
    }

    @PostMapping("delete/{id}")
    public String deleteIngredient(@PathVariable int id) {
        Ingrediente deleteIngredient = ingredienteRepository.deleteById(id);
        return "redirect:/home/Ingredients";
    }
}
