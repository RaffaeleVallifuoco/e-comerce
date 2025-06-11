package it.raffo.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.raffo.e_commerce.model.Prodotto;

public interface ProdottoRepo extends JpaRepository<Prodotto, Integer> {

    @Query(value = "SELECT * FROM prodotto WHERE LOWER(nome) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(descrizione) LIKE LOWER(CONCAT('%', :keyword, '%'))", nativeQuery = true)
    List<Prodotto> cercaProdotti(@Param("keyword") String keyword);
}
