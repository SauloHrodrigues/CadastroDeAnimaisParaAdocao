package com.animaisparaadocao.animaisparaadocao.service.implementacao;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.service.AnimalService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Override
    public ResponseEntity<AnimalResponseDto> cadastrar(AnimalRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<List<AnimalResponseDto>> todosCadastrados() {
        return null;
    }

    @Override
    public ResponseEntity<AnimalResponseDto> buscarPorId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AnimalResponseDto> atualizar(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<AnimalResponseDto> apagar(Long id) {
        return null;
    }
}