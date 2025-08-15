package com.animaisparaadocao.animaisparaadocao.dto;

import java.time.LocalDate;

public record AnimalResponseDto(

        Long id,
        String nome,
        String especie,
        String raca,
        Integer idade,
        Boolean disponivel,
        LocalDate dataDeResgate
) {}