package com.animaisparaadocao.animaisparaadocao.service.implementacao;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalJaCadastradoException;
import com.animaisparaadocao.animaisparaadocao.fixture.AnimalFixture;
import com.animaisparaadocao.animaisparaadocao.mapper.AnimalMapper;
import com.animaisparaadocao.animaisparaadocao.model.Animal;
import com.animaisparaadocao.animaisparaadocao.repository.AnimalRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceImplTest {
    @InjectMocks
    private AnimalServiceImpl service;
    @Mock
    private AnimalRepository repository;
    @Mock
    private AnimalMapper mapper;

    private AnimalRequestDto cachorroRequest;
    private AnimalRequestDto gatoRequest;
    private AnimalRequestDto papagaioRequest;

    @BeforeEach
    void setUp() {
        cachorroRequest = AnimalFixture.requestDto("Tody", "cachorro",
                "pode", 3, false, LocalDate.parse("2022-10-02"));
        gatoRequest = AnimalFixture.requestDto("Garfield", "gato", "siames",
                2, true, LocalDate.parse("2022-05-06"));
        papagaioRequest = AnimalFixture.requestDto("Catito", "ave", "shingling",
                12, false, LocalDate.parse("2013-05-10"));

    }

    @Test
    @DisplayName("Deve realizar o cadastro de um novo animal.")
    void deveCadastrarUmNovoAnimalComSucesso() {
        Animal animal = AnimalFixture.entity(1L, cachorroRequest);
        AnimalResponseDto responseDto = AnimalFixture.response(animal);

        Mockito.when(mapper.toEntity(cachorroRequest)).thenReturn(animal);
        Mockito.when(repository.save(animal)).thenReturn(animal);
        Mockito.when(mapper.toResponse(animal)).thenReturn(responseDto);

        AnimalResponseDto resposta = service.cadastrar(cachorroRequest);

        assertEquals(1L, resposta.id());
        assertEquals(cachorroRequest.nome(), resposta.nome());
        assertEquals(cachorroRequest.raca(), resposta.raca());
        assertEquals(cachorroRequest.especie(), resposta.especie());
        assertEquals(cachorroRequest.dataDeResgate(), resposta.dataDeResgate());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar cadastras animal já cadastrado.")
    void deveLancarExecaoAoTentarCadastrarAnimalJaCadastrado() {
        Animal animal = AnimalFixture.entity(1L, gatoRequest);
        Mockito.when(repository.findOneByNomeIgnoreCaseAndEspecieIgnoreCaseAndRacaIgnoreCaseAndDataDeResgate(
                        gatoRequest.nome(), gatoRequest.especie(), gatoRequest.raca(), gatoRequest.dataDeResgate()))
                .thenReturn(Optional.of(animal));

        AnimalJaCadastradoException resposta = assertThrows(AnimalJaCadastradoException.class, () -> {
            service.cadastrar(gatoRequest);
        });

        assertTrue(resposta.getMessage().contains(gatoRequest.nome()));
        assertTrue(resposta.getMessage().contains(gatoRequest.especie()));
        assertTrue(resposta.getMessage().contains(gatoRequest.raca()));

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Deve retornar todos os animais cadastrados no banco.")
    void deveRetornarTodosOsAnimaisCadastrados() {
        Animal cachorro = AnimalFixture.entity(1L,cachorroRequest);
        Animal gato = AnimalFixture.entity(2L,gatoRequest);
        List<Animal>animais = new ArrayList<>();
        animais.add(cachorro);
        animais.add(gato);
        List<AnimalResponseDto> responseDtos = new ArrayList<>();
        responseDtos.add(AnimalFixture.response(cachorro));
        responseDtos.add(AnimalFixture.response(gato));

        Mockito.when(repository.findAll()).thenReturn(animais);
        Mockito.when(mapper.toResponse(animais)).thenReturn(responseDtos);

        List<AnimalResponseDto> resposta = service.todosCadastrados();

        assertEquals(2,resposta.size());
        assertEquals(cachorro.getNome(),resposta.get(0).nome());
        assertEquals(gato.getNome(),resposta.get(1).nome());

        Mockito.verify(repository).findAll();
        Mockito.verify(mapper).toResponse(animais);
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void apagar() {
    }
}