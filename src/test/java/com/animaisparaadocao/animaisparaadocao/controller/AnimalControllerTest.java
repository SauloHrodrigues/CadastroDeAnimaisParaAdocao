package com.animaisparaadocao.animaisparaadocao.controller;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalJaCadastradoException;
import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalNaoCadastradoException;
import com.animaisparaadocao.animaisparaadocao.fixture.AnimalFixture;
import com.animaisparaadocao.animaisparaadocao.model.Animal;
import com.animaisparaadocao.animaisparaadocao.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalController.class)
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService service;

    @Autowired
    private ObjectMapper objectMapper;

    private AnimalRequestDto cachorroRequest;
    private AnimalRequestDto gatoRequest;
    private AnimalRequestDto papagaioRequest;
    private Animal cachorro;
    private Animal gato;
    private Animal papagaio;

    @BeforeEach
    void setUp() {
        cachorroRequest = AnimalFixture.requestDto("Tody", "cachorro",
                "pode", 3, false, LocalDate.parse("2022-10-02"));
        gatoRequest = AnimalFixture.requestDto("Garfield", "gato", "siames",
                2, true, LocalDate.parse("2022-05-06"));
        papagaioRequest = AnimalFixture.requestDto("Catito", "ave", "shingling",
                12, false, LocalDate.parse("2013-05-10"));

        cachorro= AnimalFixture.entity(1L,cachorroRequest);
        gato= AnimalFixture.entity(2L,gatoRequest);
        papagaio= AnimalFixture.entity(3L,papagaioRequest);

    }

    @Test
    @DisplayName("Deve realizar o cadastro de um novo animal.")
    void deveCadastrarAnimalComSucesso() throws Exception {

        AnimalRequestDto requestDto = gatoRequest;
        AnimalResponseDto responseDto = AnimalFixture.response(1L,requestDto);

        when(service.cadastrar(requestDto)).thenReturn(responseDto);

        mockMvc.perform(post("/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(responseDto.id()))
                .andExpect(jsonPath("$.nome").value(responseDto.nome()))
                .andExpect(jsonPath("$.especie").value(responseDto.especie()))
                .andExpect(jsonPath("$.raca").value(responseDto.raca()));
    }

    @Test
    @DisplayName("Deve lançar exceção quando o animal já estiver cadastrado")
    void cadastrar_deveRetornar409_AnimalJaCadastrado() throws Exception {
        AnimalRequestDto request = gatoRequest;
        String mensagemEsperada = "O animal: " + request.nome() + ", da espécie: " + request.especie()
                + ", raça: " + request.raca() + ", resgatado em: "
                + request.dataDeResgate() + " já esta cadastrado no banco.";

        when(service.cadastrar(request)).thenThrow(new AnimalJaCadastradoException(mensagemEsperada));

        mockMvc.perform(post("/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensagem").value(mensagemEsperada));
    }

    @Test
    @DisplayName("Deve retornar a lista com todos os animais já cadastrados")
    void deveRetornarListaComTodosCadastrados() throws Exception {
        List<Animal> animais = new ArrayList<>();
        animais.add(cachorro);
        animais.add(gato);
        animais.add(papagaio);

        List<AnimalResponseDto> animaisResponse = AnimalFixture.response(animais);

        when(service.todosCadastrados()).thenReturn(animaisResponse);

        mockMvc.perform(get("/animais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].id").value(cachorro.getId()))
                .andExpect(jsonPath("$.[1].id").value(gato.getId()))
                .andExpect(jsonPath("$.[2].id").value(papagaio.getId())
                );
    }

    @Test
    @DisplayName("Deve retornar um animal com determinado id, já cadastrado no banco.")
    void buscarPorId() throws Exception {
        Long idBuscado = gato.getId();
        Animal animal = gato;
        AnimalResponseDto responseDto= AnimalFixture.response(animal);

        when(service.buscarPorId(idBuscado)).thenReturn(responseDto);

        mockMvc.perform(get("/animais/{id}",idBuscado))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(responseDto.id()))
                .andExpect(jsonPath("$.nome").value(responseDto.nome()))
                .andExpect(jsonPath("$.especie").value(responseDto.especie()))
                .andExpect(jsonPath("$.raca").value(responseDto.raca()))
                .andExpect(jsonPath("$.dataDeResgate").value(responseDto.dataDeResgate().toString())
                );
    }


    @Test
    @DisplayName("Deve lançar exceção de animal não encontrado ao buscar por id .")
    void deveLancarExcecaoAoBuscarPorIdInvalido() throws Exception {
        Long idBuscado = 1L;
        AnimalRequestDto request = gatoRequest;
        String mensagemEsperada = "O id: \'"
                + idBuscado + "\' não corresponde a nenhum animal cadastrado no banco";

        when(service.buscarPorId(idBuscado)).thenThrow(new AnimalNaoCadastradoException(mensagemEsperada));

        mockMvc.perform(get("/animais/{id}",idBuscado))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensagem").value(mensagemEsperada));
    }

    @Test
    void atualizar() {
    }

    @Test
    void apagar() {
    }
}