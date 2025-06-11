package it.raffo.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.raffo.e_commerce.model.Categoria;

public interface CategoryRepo extends JpaRepository<Categoria, Integer> {
    public String findByNome(String nome);

}
