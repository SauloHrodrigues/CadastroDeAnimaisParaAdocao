package com.animaisparaadocao.animaisparaadocao.service;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimalService {
    AnimalResponseDto cadastrarNovoAnimal(AnimalRequestDto dto);
    Page<AnimalResponseDto> retornaTodosAnimaisCadastrados(Pageable pageable);
    AnimalResponseDto buscarAnimalNoBancoPorId(Long id );
    AnimalResponseDto atualizarDadosDoAnimal(Long id, AnimalAtualizarDto dto);
    void deletarAnimalDoBanco(Long id);
}