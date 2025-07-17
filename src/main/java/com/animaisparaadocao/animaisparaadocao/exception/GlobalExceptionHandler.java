package com.animaisparaadocao.animaisparaadocao.exception;

import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalJaCadastradoException;
import com.animaisparaadocao.animaisparaadocao.exception.especies.AnimalNaoCadastradoException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnimalJaCadastradoException.class)
    public ResponseEntity<Object> handlerAnimalJaCadastradoException(AnimalJaCadastradoException excecao){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("Erro. ", excecao.getMessage()));
    }
    @ExceptionHandler(AnimalJaCadastradoException.class)
    public ResponseEntity<Object> handlerAnimalNaoCadastradoException(AnimalNaoCadastradoException excecao){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Erro. ", excecao.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(erro -> {
            erros.put(erro.getField(), erro.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(erros);
    }

    static class ErrorResponse {
        private String tipoErro;
        private String mensagem;

        public ErrorResponse(String tipoErro, String mensagem) {
            this.tipoErro = tipoErro;
            this.mensagem = mensagem;
        }

        public String getTipoErro() {
            return tipoErro;
        }

        public String getMensagem() {
            return mensagem;
        }
    }
}
