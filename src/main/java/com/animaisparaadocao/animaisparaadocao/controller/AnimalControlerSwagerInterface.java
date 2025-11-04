package com.animaisparaadocao.animaisparaadocao.controller;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Animais", description = "Endpoints para gestão de animais: cadastro, listagem, atualização e exclusão.")
public interface AnimalControlerSwagerInterface {

    @Operation(summary = "Cadastra um novo animal")
    @ApiResponse(responseCode = "201", description = "Animal cadastrado com sucesso.",
            content = @Content(schema = @Schema(implementation = AnimalResponseDto.class)))
    ResponseEntity<AnimalResponseDto> cadastrarNovoAnimal(@Valid @RequestBody AnimalRequestDto dto);

    @Operation(summary = "Lista todos os animais cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso.",
            content = @Content(schema = @Schema(implementation = AnimalResponseDto.class)))
    ResponseEntity<Page<AnimalResponseDto>> retornarTodosAnimaisCadastrados(
            @Parameter(description = "Parâmetros de paginação e ordenação")
            @PageableDefault(size = 10, sort = {"nome"}) Pageable pageable);

    @Operation(summary = "Busca um animal pelo ID")
    @ApiResponse(responseCode = "200", description = "Retorna os dados do animal.",
            content = @Content(schema = @Schema(implementation = AnimalResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Animal não encontrado.")
    ResponseEntity<AnimalResponseDto> buscarUmAnimalPorId(
            @Parameter(description = "ID do animal", example = "42") @PathVariable Long id);

    @Operation(summary = "Atualiza um animal existente")
    @ApiResponse(responseCode = "200", description = "Retorna os dados atualizados do animal.",
            content = @Content(schema = @Schema(implementation = AnimalResponseDto.class)))
    ResponseEntity<AnimalResponseDto> atualizarUmAnimal(
            @Parameter(description = "ID do animal", example = "42")
            @PathVariable Long id,
            @Valid @RequestBody AnimalAtualizarDto atualizacoes);

    @Operation(summary = "Exclui um animal existente")
    @ApiResponse(responseCode = "204", description = "Animal excluído com sucesso.")
    @ApiResponse(responseCode = "404", description = "Animal não encontrado.")
    ResponseEntity<Void> apagar(@Parameter(description = "ID do animal", example = "42")
                                @PathVariable Long id);
}