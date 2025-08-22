package com.animaisparaadocao.animaisparaadocao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Objeto para atualização de um animal. Informar apenas os campos a serem alterados.")
public record AnimalAtualizarDto(

        @Schema(description = "Nome do animal", example = "Rex")
        @Size(min = 2, message = "O nome deve ter pelo menos 2 caracteres")
        String nome,

        @Schema(description = "Espécie do animal", example = "Cachorro")
        String especie,

        @Schema(description = "Raça do animal", example = "Labrador")
        String raca,

        @Schema(description = "Idade do animal em anos", example = "3")
        Integer idade,

        @Schema(description = "Disponibilidade do animal para adoção", example = "true")
        Boolean disponivel,

        @Schema(description = "Data em que o animal foi resgatado", type = "string", pattern = "dd/MM/yyyy", example = "22/08/2025")
        @PastOrPresent(message = "A data não pode ser no futuro")
        LocalDate dataDeResgate
) {}
