package com.animaisparaadocao.animaisparaadocao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Objeto de saída representando um animal cadastrado.")
public record AnimalResponseDto(

        @Schema(description = "ID único do animal", example = "42")
        Long id,

        @Schema(description = "Nome do animal", example = "Rex")
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataDeResgate
) {}