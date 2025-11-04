package com.animaisparaadocao.animaisparaadocao.exception.especies;

import java.time.LocalDate;

public class AnimalJaCadastradoException extends RuntimeException{
    public AnimalJaCadastradoException(String nome, String especie, String raca, LocalDate data){
        super("O animal: "+nome+", da espécie: " + especie + ", raça: " + raca + ", resgatado em: "
                + data + " já esta cadastrado no banco.");
    }
}