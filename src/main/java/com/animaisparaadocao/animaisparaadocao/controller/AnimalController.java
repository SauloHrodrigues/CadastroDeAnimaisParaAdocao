package com.animaisparaadocao.animaisparaadocao.controller;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.service.AnimalService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/animais")
public class AnimalController {
    @Autowired
    private AnimalService service;

    @PostMapping
    public ResponseEntity<AnimalResponseDto> cadastrar(@Valid @RequestBody AnimalRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDto>> todosCadastrados(){
        List<AnimalResponseDto>resposta = service.todosCadastrados();
        return ResponseEntity.status(HttpStatus.OK).body(resposta);
    }

    public ResponseEntity<AnimalResponseDto> buscarPorId(Long id ){
        return null;
    }

    public ResponseEntity<AnimalResponseDto> atualizar(Long id){
        return null;
    }
    public ResponseEntity<AnimalResponseDto> apagar(Long id){
        return null;
    }
}
