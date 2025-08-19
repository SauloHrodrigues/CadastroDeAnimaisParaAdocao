package com.animaisparaadocao.animaisparaadocao.integracao;

import com.animaisparaadocao.animaisparaadocao.auxiliar.PageResponse;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.fixture.AnimalFixture;
import com.animaisparaadocao.animaisparaadocao.model.Animal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"/resetDB.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AnimaisParaAocaoIt {

    @Autowired
    private TestRestTemplate template;

    @Test
    @DisplayName("Deve cadastrar um novo animal com sucesso ")
    public void deveCadastrarUmNovoAnimalComSucesso() {
        AnimalRequestDto dto = AnimalFixture.requestDto("Toto", "cachorro", "vira-lata", 3, true, LocalDate.parse("2022-10-02"));
        ResponseEntity<AnimalResponseDto> resposta = template.postForEntity("/animais", dto,
                AnimalResponseDto.class);
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resposta.getBody().id()).isNotNull();
        assertThat(resposta.getBody().nome()).isEqualTo(dto.nome().toLowerCase());
    }

    @Test
    @DisplayName("Deve retornar uma lista de animais cadastrados no banco de dados")
    @Sql(scripts = {"/cria_tres_animais_no_banco.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void deveRetornarUmaListaDeAnimaisCadastradosNoBancoDedados() {
        ResponseEntity<PageResponse<AnimalResponseDto>> resposta =
                template.exchange(
                        "/animais?page=0&size=3",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<PageResponse<AnimalResponseDto>>() {
                        }
                );
        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody()).isNotNull();
        assertThat(resposta.getBody().getContent()).hasSize(3);
        assertThat(resposta.getBody().getTotalElements()).isEqualTo(3);
    }

    @Test
    @DisplayName("Deve buscar um animal no banco pelo id")
    public void deveBuscarUmAnimalNoBancoPeloId() {
        AnimalRequestDto dto = AnimalFixture.requestDto("Toto", "cachorro", "vira-lata", 3, true, LocalDate.parse("2022-10-02"));
        ResponseEntity<AnimalResponseDto> animalCadastrado = template.postForEntity("/animais", dto,
                AnimalResponseDto.class);
        Long idBuscaddo = animalCadastrado.getBody().id();

        ResponseEntity<AnimalResponseDto> resposta = template.getForEntity("/animais/" + idBuscaddo,
                AnimalResponseDto.class);

        assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resposta.getBody().nome()).isEqualTo(dto.nome().toLowerCase());
        assertThat(resposta.getBody().idade()).isEqualTo(dto.idade());
        assertThat(resposta.getBody().especie()).isEqualTo(dto.especie());
    }

    @Test
    @DisplayName("Deve atualizar os dados de um animal existente")
    void deveAtualizarAnimal() {
        AnimalRequestDto dto = AnimalFixture.requestDto("Toto", "cachorro", "vira-lata", 3, true, LocalDate.parse("2022-10-02"));
        ResponseEntity<AnimalResponseDto> animalCadastrado = template.postForEntity("/animais", dto,
                AnimalResponseDto.class);
        Long idBuscaddo = animalCadastrado.getBody().id();

        AnimalAtualizarDto atualizacoesDto = new AnimalAtualizarDto(null, null, "pode francês",
                5, false, null);

        ResponseEntity<AnimalResponseDto> resposta = template.exchange(
                "/animais/" + idBuscaddo,
                HttpMethod.PUT,
                new HttpEntity<>(atualizacoesDto),
                AnimalResponseDto.class
        );
    }

        @Test
        @DisplayName("Deve apagar um animal existente")
        void deveApagarAnimalPorId () {
            AnimalRequestDto dto = AnimalFixture.requestDto("Toto", "cachorro", "vira-lata", 3, true, LocalDate.parse("2022-10-02"));
            ResponseEntity<AnimalResponseDto> animalCadastrado = template.postForEntity("/animais", dto,
                    AnimalResponseDto.class);
            Long idBuscaddo = animalCadastrado.getBody().id();

            ResponseEntity<Void> resposta = template.exchange(
                    "/animais/" + idBuscaddo,
                    HttpMethod.DELETE,
                    HttpEntity.EMPTY,
                    Void.class
            );

            assertThat(resposta.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        }

    }