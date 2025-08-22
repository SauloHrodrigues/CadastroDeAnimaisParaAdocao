package com.animaisparaadocao.animaisparaadocao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Objeto de entrada para cadastro de um novo animal.")
public record AnimalRequestDto(
        @Schema(description = "Nome do animal", example = "Rex")
        @NotBlank(message = "O nome do animal é campo de preenchimento obrigatório.")
        @Size(min = 2, message = "O nome deve ter pelo menos 2 caracteres")
        String nome,
        @Schema(description = "Espécie do animal", example = "Cachorro")
        @NotBlank(message = "A especie do animal é campo de preenchimento obrigatório.")
        String especie,
        @Schema(description = "Raça do animal", example = "Labrador")
        String raca,
        @Schema(description = "Idade do animal em anos", example = "3")
        Integer idade,
        @Schema(description = "Disponibilidade para adoção", example = "true")
        Boolean disponivel,
        @NotNull(message = "A data do resgate é campo de preenchimento obrigatório.")
        @PastOrPresent(message = "A data não pode ser no futuro")
        LocalDate dataDeResgate
) {}
