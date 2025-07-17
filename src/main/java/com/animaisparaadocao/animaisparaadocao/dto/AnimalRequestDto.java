package com.animaisparaadocao.animaisparaadocao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record AnimalRequestDto(
        @NotBlank(message = "O nome do animal é campo de preenchimento obrigatório.")
        @Size(min = 2, message = "O nome deve ter pelo menos 2 caracteres")
        String nome,
        @NotBlank(message = "A especie do animal é campo de preenchimento obrigatório.")
        String especie,
        String raca,
        int idade,
        Boolean disponivel,
        @NotNull(message = "A data do resgate é campo de preenchimento obrigatório.")
        LocalDate dataDeResgate
) {
    public AnimalRequestDto {
        if(disponivel ==null){
            disponivel=true;
        }
    }
}
