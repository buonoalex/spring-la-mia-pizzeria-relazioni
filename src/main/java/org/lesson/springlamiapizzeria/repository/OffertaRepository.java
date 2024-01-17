package org.lesson.springlamiapizzeria.repository;

import org.lesson.springlamiapizzeria.model.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OffertaRepository extends JpaRepository<Offerta, Integer> {
    Optional<Offerta> findById(int name);

}
