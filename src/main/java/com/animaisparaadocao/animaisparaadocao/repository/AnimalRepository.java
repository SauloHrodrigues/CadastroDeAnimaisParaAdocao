package com.animaisparaadocao.animaisparaadocao.repository;

import com.animaisparaadocao.animaisparaadocao.model.Animal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {
    List<Optional<Animal>> findByNome(String nome);
    Optional<Animal>findOneByNomeIgnoreCaseAndEspecieIgnoreCaseAndRacaIgnoreCaseAndDataDeResgate(String nome,String especie, String raca, LocalDate dataDeResgate);
}