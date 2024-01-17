package org.lesson.springlamiapizzeria.controller;

import jakarta.validation.Valid;
import org.lesson.springlamiapizzeria.model.Offerta;
import org.lesson.springlamiapizzeria.model.Pizza;
import org.lesson.springlamiapizzeria.repository.OffertaRepository;
import org.lesson.springlamiapizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public String saveOffert(@Valid @ModelAttribute("offerta") Offerta formOffert, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", formOffert.getPizza());
            return "offerta/createOffert";
        }
        //Logica controllo date
        if (formOffert.getStartDate().isBefore(LocalDate.now())) {
            bindingResult.addError(new FieldError("offerta", "startDate", formOffert.getStartDate(), false, null, null,
                    "la data è gia passata"));
            return "offerta/createOffert";
        } else if (formOffert.getStartDate().isAfter(formOffert.getEndDate())) {
            bindingResult.addError(new FieldError("offerta", "startDate", formOffert.getStartDate(), false, null, null,
                    "la data selezionata non può accadere dopo la fine dell'offerta"));
            return "offerta/createOffert";
        } else {
            Offerta saveOffert = offertaRepository.save(formOffert);
            return "redirect:/home/pizzaList/details/" + saveOffert.getPizza().getName();
        }
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable int id, Model model) {
        //Trovare offerta
        Optional<Offerta> offertaRecovery = offertaRepository.findById(id);
        //Prendiamo i dati
        Offerta offerta = offertaRecovery.get();
        //li passiamo al model
        model.addAttribute("offerta", offerta);

        return "offerta/editOffert";
    }

    @PostMapping("/edit/{id}")
    public String saveEditOffer(@PathVariable int id, @Valid @ModelAttribute("offerta") Offerta editOffert, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offerta/editOffert";
        } else {
            offertaRepository.save(editOffert);
            return "redirect:/home/pizzaList/details/" + editOffert.getPizza().getName();
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteOffer(@PathVariable int id, Model model) {
        Optional<Offerta> offertaRecovery = offertaRepository.findById(id);
        Offerta offerta = offertaRecovery.get();
        offertaRepository.deleteById(id);
        return "redirect:/home/pizzaList/details/" + offerta.getPizza().getName();
    }
}
