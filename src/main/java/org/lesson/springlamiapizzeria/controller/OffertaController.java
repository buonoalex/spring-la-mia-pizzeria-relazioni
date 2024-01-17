package org.lesson.springlamiapizzeria.controller;

import jakarta.validation.Valid;
import org.lesson.springlamiapizzeria.model.Offerta;
import org.lesson.springlamiapizzeria.model.Pizza;
import org.lesson.springlamiapizzeria.repository.OffertaRepository;
import org.lesson.springlamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/home/offerta")
public class OffertaController {
    @Autowired
    private OffertaRepository offertaRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create/{name}")
    public String pizzaOffert(@PathVariable String name, Model model) {
        //recuoero la pizza
        Optional<Pizza> pizzaRecovery = pizzaRepository.findByName(name);
        Pizza pizza = pizzaRecovery.get();
        //creao oggetto offerta
        Offerta offerta = new Offerta();
        offerta.setPizza(pizza);
        //Inserisco nel model
        model.addAttribute("offerta", offerta);
        model.addAttribute("pizza", pizza);
        return "offerta/createOffert";
    }

    @PostMapping("/create")
    public String saveOffert(@Valid @ModelAttribute("offerta") Offerta formOffert, Model model) {
        Offerta saveOffert = offertaRepository.save(formOffert);
        return "redirect:/home";
    }

}
