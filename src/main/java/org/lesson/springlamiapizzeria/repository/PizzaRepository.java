package org.lesson.springlamiapizzeria.repository;

import org.lesson.springlamiapizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, String> {
    //Creo le mie query personali
    Optional<Pizza> findByName(String name);

    List<Pizza> findByNameContaining(String nameFind);

    List<Pizza> findByPriceLessThanEqual(BigDecimal numberPrice);

    List<Pizza> findByNameAndPrice(String nameFind, BigDecimal numberPrice);


}

