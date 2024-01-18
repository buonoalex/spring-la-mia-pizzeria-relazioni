package org.lesson.springlamiapizzeria.repository;

import org.lesson.springlamiapizzeria.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
