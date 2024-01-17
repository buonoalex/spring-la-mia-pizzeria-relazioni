package org.lesson.springlamiapizzeria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Offerta {

    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @NotNull(message = "la data non può essere vuota")
    private LocalDate startDate;
    @Column(nullable = false)
    @NotNull(message = "la data non può essere vuota")
    private LocalDate endDate;
    @Lob
    @Column(nullable = false)
    @NotEmpty(message = "il titolo è obbligatorio")
    private String title;

    //Relazioni
    @ManyToOne
    private Pizza pizza;

    //Costruttore

    //Metodi
    public boolean Control_date(LocalDate data1, LocalDate data2) {
        if (data1.isBefore(LocalDate.now()) || (data1.isAfter(data2))) {
            return true;
        } else if (data2.isBefore(LocalDate.now()) || (data2.isBefore(data1))) {
            return true;
        } else {
            return false;
        }
    }

    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
