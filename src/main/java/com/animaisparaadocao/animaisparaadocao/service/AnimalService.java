package com.animaisparaadocao.animaisparaadocao.service;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimalService {
    AnimalResponseDto cadastrar(AnimalRequestDto dto);
    Page<AnimalResponseDto> todosCadastrados(Pageable pageable);
    AnimalResponseDto buscarPorId(Long id );
    AnimalResponseDto atualizar(Long id, AnimalAtualizarDto dto);
    void apagar(Long id);
}