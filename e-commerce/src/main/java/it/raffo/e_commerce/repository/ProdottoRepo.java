package it.raffo.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.raffo.e_commerce.model.Prodotto;

public interface ProdottoRepo extends JpaRepository<Prodotto, Integer> {

    List<Prodotto> findByEvidenceTrue();

    List<Prodotto> findByCategoriaId(Integer categoriaId);

    @Query("SELECT p FROM Prodotto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.descrizione) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.marca.nome) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Prodotto> cercaProdotti(@Param("keyword") String keyword);

}
