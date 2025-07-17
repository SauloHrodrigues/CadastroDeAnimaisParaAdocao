package com.animaisparaadocao.animaisparaadocao.service;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AnimalService {
    ResponseEntity<AnimalResponseDto> cadastrar(AnimalRequestDto dto);
    ResponseEntity<List<AnimalResponseDto>> todosCadastrados();
    ResponseEntity<AnimalResponseDto> buscarPorId(Long id );
    ResponseEntity<AnimalResponseDto> atualizar(Long id);
    ResponseEntity<AnimalResponseDto> apagar(Long id);
}