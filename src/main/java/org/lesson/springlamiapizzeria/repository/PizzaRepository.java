package org.lesson.springlamiapizzeria.repository;

import org.lesson.springlamiapizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, String> {

}

