package com.animaisparaadocao.animaisparaadocao.service.implementacao;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalJaCadastradoException;
import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalNaoCadastradoException;
import com.animaisparaadocao.animaisparaadocao.mapper.AnimalMapper;
import com.animaisparaadocao.animaisparaadocao.model.Animal;
import com.animaisparaadocao.animaisparaadocao.repository.AnimalRepository;
import com.animaisparaadocao.animaisparaadocao.service.AnimalService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalMapper mapper = AnimalMapper.INSTANCE;
    @Autowired
    private AnimalRepository repository;

    @Override
    public AnimalResponseDto cadastrar(AnimalRequestDto dto) {
        validaAnimalNaoCadastrado(dto.nome(), dto.especie(), dto.raca(), dto.dataDeResgate());
        Animal animal = mapper.toEntity(dto);
        Animal animalSalvo = repository.save(animal);
        return mapper.toResponse(animalSalvo);
    }

    @Override
    public List<AnimalResponseDto> todosCadastrados() {
        List<Animal> animais = repository.findAll();
        List<AnimalResponseDto> responseDto = mapper.toResponse(animais);
        return responseDto;
    }

    @Override
    public AnimalResponseDto buscarPorId(Long id) {
        Animal animal = validaAnimalCadastrado(id);
        return mapper.toResponse(animal);
    }

    @Override
    public AnimalResponseDto atualizar(Long id, AnimalAtualizarDto atualizacoes) {
        Animal animal = validaAnimalCadastrado(id);
        mapper.updateAnimal(animal,atualizacoes);
        repository.save(animal);
        return mapper.toResponse(animal);
    }

    @Override
    public void apagar(Long id) {
        Animal animal = validaAnimalCadastrado(id);
        repository.delete(animal);
    }

    private Animal validaAnimalCadastrado(Long id) {
        return repository.findById(id).orElseThrow(() -> new AnimalNaoCadastradoException("O id: \'"
                + id + "\' não corresponde a nenhum animal cadastrado no banco"));
    }

    private void validaAnimalNaoCadastrado(String nome, String especie, String raca, LocalDate data) {
        Optional<Animal> animal = repository.findOneByNomeIgnoreCaseAndEspecieIgnoreCaseAndRacaIgnoreCaseAndDataDeResgate(
                nome,especie, raca, data);
        if (animal.isPresent()) {
            throw new AnimalJaCadastradoException("O animal: "+nome+", da espécie: " + especie + ", raça: " + raca + ", resgatado em: "
                    + data + " já esta cadastrado no banco.");
        }
    }
}