package com.animaisparaadocao.animaisparaadocao.controller;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalJaCadastradoException;
import com.animaisparaadocao.animaisparaadocao.fixture.AnimalFixture;
import com.animaisparaadocao.animaisparaadocao.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
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

        mockMvc.perform(post("/animais") // Verifique se o path correto é "/animal"
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.mensagem").value(mensagemEsperada));
    }

    @Test
    void cadastrar() {
    }

    @Test
    void todosCadastrados() {
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