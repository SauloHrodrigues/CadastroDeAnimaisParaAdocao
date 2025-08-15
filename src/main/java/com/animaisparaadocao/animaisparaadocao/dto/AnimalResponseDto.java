package com.animaisparaadocao.animaisparaadocao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record AnimalResponseDto(

        Long id,
        String nome,
        String especie,
        String raca,
        Integer idade,
        Boolean disponivel,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataDeResgate
) {}