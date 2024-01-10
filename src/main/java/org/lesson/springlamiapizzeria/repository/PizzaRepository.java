package org.lesson.springlamiapizzeria.repository;

import org.lesson.springlamiapizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, String> {
    Optional<Pizza> findByName(String name);

}

