package com.animaisparaadocao.animaisparaadocao.fixture;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.model.Animal;
import jakarta.validation.constraints.NotBlank;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class AnimalFixture {
    public static AnimalRequestDto requestDto(String nome,String especie, String raca, int idade,
            Boolean disponivel, LocalDate dataDeResgate ){
        return new AnimalRequestDto(nome,especie,raca,idade, disponivel,dataDeResgate);
    }

    public static Animal entity(Long id, AnimalRequestDto dto){

        Animal entidade = new Animal(
                dto.nome(), dto.especie(), dto.raca(), dto.idade(), dto.disponivel(),
                dto.dataDeResgate());
        try {
            Field field = Animal.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(entidade, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public static AnimalResponseDto response(Animal animal){
        return new AnimalResponseDto(
                animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca(),
                animal.getIdade(), animal.getDisponivel(),animal.getDataDeResgate());
    }
}
