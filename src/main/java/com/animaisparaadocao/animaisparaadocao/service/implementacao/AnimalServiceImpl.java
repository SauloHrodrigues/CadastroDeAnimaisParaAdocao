package com.animaisparaadocao.animaisparaadocao.service.implementacao;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.service.AnimalService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Override
    public AnimalResponseDto cadastrar(AnimalRequestDto dto) {
        return null;
    }

    @Override
    public List<AnimalResponseDto> todosCadastrados() {
        return null;
    }

    @Override
    public AnimalResponseDto buscarPorId(Long id) {
        return null;
    }

    @Override
    public AnimalResponseDto atualizar(Long id) {
        return null;
    }

    @Override
    public AnimalResponseDto apagar(Long id) {
        return null;
    }
}