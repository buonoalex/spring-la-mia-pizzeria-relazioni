package org.lesson.springlamiapizzeria.controller;

import jakarta.validation.Valid;
import org.lesson.springlamiapizzeria.model.Pizza;
import org.lesson.springlamiapizzeria.repository.OffertaRepository;
import org.lesson.springlamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/home/pizzaList")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OffertaRepository offertaRepository;

    @GetMapping
    public String pizzaList(@RequestParam(required = false) String nameFind, @RequestParam(required = false) BigDecimal numberFind, Model model) {
        List<Pizza> pizzaList = null;

        if ((nameFind != null) && (numberFind == null)) {
            pizzaList = pizzaRepository.findByNameContaining(nameFind);
        } else if (numberFind != null && nameFind.isBlank()) {
            pizzaList = pizzaRepository.findByPriceLessThanEqual(numberFind);
        } else {
            pizzaList = pizzaRepository.findAll();
        }

        // aggiungo la lista di libri agli attributi del Model
        model.addAttribute("pizzaList", pizzaList);

        return "pizza/pizzaList";
    }

    @GetMapping("/details/{name}")
    public String pizzaDetail(@PathVariable String name, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(name);
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

    @GetMapping("/create")
    public String addPizza(Model model) {
        Pizza pizza = new Pizza();
        model.addAttribute("pizza", pizza);
        return "pizza/createPizza";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza userPizza, BindingResult bindingResult) {
        // valido i dati del Book, cioè verifico se la mappa BindingResult ha errori
        if (bindingResult.hasErrors()) {
            return "pizza/createPizza";
        }
        Optional<Pizza> pizzaWithName = pizzaRepository.findByName(userPizza.getName());
        if (pizzaWithName.isPresent()) {
            // se esiste già ritorno un errore
            bindingResult.addError(new FieldError("pizza", "name", userPizza.getName(), false, null, null,
                    "il nome della pizza già è presente nel menu'"));
            return "pizza/createPizza";
        } else {
            Pizza savedPizza = pizzaRepository.save(userPizza);
            return "redirect:/home/pizzaList/details/" + savedPizza.getName();
        }
    }

    @GetMapping("/edit/{name}")
    public String edit(@PathVariable String name, Model model) {
        Optional<Pizza> pizzaEdit = pizzaRepository.findByName(name);
        if (pizzaEdit.isPresent()) {
            model.addAttribute("pizza", pizzaEdit.get());
            return "pizza/editPizza";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{name}")
    public String updatePizza(@PathVariable String name, @Valid @ModelAttribute("pizza") Pizza userPizza, BindingResult bindingResult) {
        //Verificare se sono validi
        if (bindingResult.hasErrors()) {
            return "pizza/editPizza";
        } else {
            Optional<Pizza> pizzaEdit = pizzaRepository.findByName(name);
            Pizza pizzaToEdit = pizzaEdit.get();
            pizzaRepository.deleteById(pizzaToEdit.getName());
            Pizza modifyPizza = pizzaRepository.save(userPizza);
            return "redirect:/home/pizzaList/details/" + modifyPizza.getName();
        }
    }


    @PostMapping("/delete/{name}")
    public String deletePizza(@PathVariable String name, RedirectAttributes redirectAttributes) {
        offertaRepository.deleteByPizzaName(name);
        pizzaRepository.deleteById(name);
        redirectAttributes.addFlashAttribute("redirectAttribute", "La pizza" + name + "è stato cancellato correttamente");
        return "redirect:/home/pizzaList";

    }
}
