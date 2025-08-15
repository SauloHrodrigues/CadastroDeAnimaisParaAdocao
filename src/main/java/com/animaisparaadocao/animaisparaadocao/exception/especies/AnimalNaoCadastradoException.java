package com.animaisparaadocao.animaisparaadocao.exception.especies;

public class AnimalNaoCadastradoException extends RuntimeException{
    public AnimalNaoCadastradoException(Long id){
        super("O id: \'"+ id + "\' não corresponde a nenhum animal cadastrado no banco");
    }

    public AnimalNaoCadastradoException(String nome){
        super("O animal de nome: \'"+ nome + "\' não corresponde a nenhum animal cadastrado no banco");
    }
}