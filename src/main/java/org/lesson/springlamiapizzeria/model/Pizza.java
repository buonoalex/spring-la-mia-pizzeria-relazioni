package org.lesson.springlamiapizzeria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.math.BigDecimal;

@Entity
public class Pizza {
    //Attributi
    @Id
    private String name;
    @Lob
    private String description;
    private String picture_url;
    @Column(nullable = false)
    private BigDecimal price;

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

    public String getPictureUrl() {
        return picture_url;
    }

    public void setPictureUrl(String pictureUrl) {
        this.picture_url = pictureUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
