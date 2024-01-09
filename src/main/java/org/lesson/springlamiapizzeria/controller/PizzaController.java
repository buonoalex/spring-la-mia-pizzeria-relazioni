package org.lesson.springlamiapizzeria.controller;

import org.lesson.springlamiapizzeria.model.Pizza;
import org.lesson.springlamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/pizzaList")
public class PizzaController {

    @Autowired
    private PizzaRepository bookRepository;

    @GetMapping
    public String pizzaList(Model model) {

        // recupero la lista di libri dal database
        List<Pizza> pizzaList = bookRepository.findAll();

        // aggiungo la lista di libri agli attributi del Model
        model.addAttribute("pizzaList", pizzaList);

        return "pizza/pizzaList";
    }

    @GetMapping("/details/{name}")
    public String pizzaDetail(@PathVariable String name, Model model) {
        Optional<Pizza> result = bookRepository.findById(name);
        // verifico se il Book è stato trovato
        if (result.isPresent()) {
            // estraggo il Book dall'Optional
            Pizza pizza = result.get();
            // aggiungo al Model l'attributo con il Book
            model.addAttribute("pizza", pizza);
            // restituisco il template
            return "pizza/pizzaDetail";
        } else {
            // gestisco il caso in cui nel database un Book con quell'id non c'è
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with name " + name + " not found");
        }
    }

}
