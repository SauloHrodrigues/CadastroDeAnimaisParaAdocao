package com.animaisparaadocao.animaisparaadocao.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AnimalAtualizarDto(
        @Size(min = 2, message = "O nome deve ter pelo menos 2 caracteres")
        String nome,
        String especie,
        String raca,
        Integer idade,
        Boolean disponivel,

        @PastOrPresent(message = "A data não pode ser no futuro")
        LocalDate dataDeResgate
) {
}
