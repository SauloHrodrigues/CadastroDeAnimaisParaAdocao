package com.animaisparaadocao.animaisparaadocao.service;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import java.util.List;

public interface AnimalService {
    AnimalResponseDto cadastrar(AnimalRequestDto dto);
    List<AnimalResponseDto> todosCadastrados();
    AnimalResponseDto buscarPorId(Long id );
    AnimalResponseDto atualizar(Long id, AnimalAtualizarDto dto);
    void apagar(Long id);
}