package org.lesson.springlamiapizzeria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Pizza {
    //Attributi

    @Id
    @NotEmpty(message = "Il nome della pizza è obbligatorio")
    private String name;
    @Lob
    @Column(nullable = false)
    @NotEmpty(message = "la descrizione della pizza è obbligatoria")
    private String description;
    private String picture_url;
    @Column(nullable = false)
    @DecimalMin(value = "0", message = "il prezzo non può essere minore di 0")
    @DecimalMax(value = "15", message = "il prezzo non può essere maggiore di 15")
    private BigDecimal price;

    //Relazioni

    @OneToMany(mappedBy = "pizza")
    private List<Offerta> offertaList;

    @ManyToMany(mappedBy = "ingrediente")
    private List<Ingrediente> ingredienteList;

    //Costruttori utilizziamo quello di default

    //Metodi

    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Offerta> getOffertaList() {
        return offertaList;
    }

    public void setOffertaList(List<Offerta> offertaList) {
        this.offertaList = offertaList;
    }

    public List<Ingrediente> getIngredienteList() {
        return ingredienteList;
    }

    public void setIngredienteList(List<Ingrediente> ingredienteList) {
        this.ingredienteList = ingredienteList;
    }
}
