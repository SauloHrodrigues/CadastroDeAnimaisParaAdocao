package com.animaisparaadocao.animaisparaadocao.fixture;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.model.Animal;
import jakarta.validation.constraints.NotBlank;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public static AnimalResponseDto response(Long id, AnimalRequestDto dto){
        Animal animal = entity(id,dto);
        return response(animal);
    }
    public static AnimalResponseDto response(Animal animal){
        return new AnimalResponseDto(
                animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca(),
                animal.getIdade(), animal.getDisponivel(),animal.getDataDeResgate());
    }

    public static List<AnimalResponseDto> response(List<Animal> animais){
        List<AnimalResponseDto>responseDtos = new ArrayList<>();
        for (Animal animal:animais){
            responseDtos.add(response(animal));
        }
        return responseDtos;
    }

    public static AnimalAtualizarDto atualizarDto(String nome, String especie,String raca,
                                                  int idade, boolean disponivel,LocalDate dataDeResgate){

        return new AnimalAtualizarDto(nome,especie,raca,idade,disponivel,dataDeResgate);
    }
    public static AnimalResponseDto response(Animal animal, AnimalAtualizarDto atualizacoes){

        if ( atualizacoes.nome() != null ) {
            animal.setNome( atualizacoes.nome() );
        }
        if ( atualizacoes.especie() != null ) {
            animal.setEspecie( atualizacoes.especie() );
        }
        if ( atualizacoes.raca() != null ) {
            animal.setRaca( atualizacoes.raca() );
        }

        if ( atualizacoes.idade() != 0 ) {
            animal.setIdade( atualizacoes.idade());
        }

        if ( atualizacoes.disponivel() != null ) {
            animal.setDisponivel( atualizacoes.disponivel() );
        }
        if ( atualizacoes.dataDeResgate() != null ) {
            animal.setDataDeResgate( atualizacoes.dataDeResgate() );
        }
        return response(animal);
    }

}
