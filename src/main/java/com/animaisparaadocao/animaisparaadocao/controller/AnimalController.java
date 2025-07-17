package com.animaisparaadocao.animaisparaadocao.controller;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/animal")
public class AnimalController {

    public ResponseEntity<AnimalResponseDto> cadastrar(AnimalRequestDto dto){
        return null;
    }
    public ResponseEntity<List<AnimalResponseDto>> todosCadastrados(){
        return null;
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
