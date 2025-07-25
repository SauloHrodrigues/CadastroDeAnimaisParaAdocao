package com.animaisparaadocao.animaisparaadocao.exception.especies;

public class AnimalNaoCadastradoException extends RuntimeException{
    public AnimalNaoCadastradoException(String mensagem){
        super(mensagem);
    }
}