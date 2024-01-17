package org.lesson.springlamiapizzeria.repository;

import org.lesson.springlamiapizzeria.model.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OffertaRepository extends JpaRepository<Offerta, Integer> {
    Optional<Offerta> findById(int name);

    @Transactional
        //Questa operazione di delete viene utilizzata attraverso una transazione
    void deleteByPizzaName(String pizzaName);

}
