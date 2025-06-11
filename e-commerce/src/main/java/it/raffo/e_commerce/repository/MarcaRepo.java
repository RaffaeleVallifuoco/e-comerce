package it.raffo.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.raffo.e_commerce.model.Marca;

public interface MarcaRepo extends JpaRepository<Marca, Integer> {
    public String findByNome(String nome);

}
