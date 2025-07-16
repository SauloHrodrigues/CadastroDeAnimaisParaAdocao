package com.animaisparaadocao.animaisparaadocao.mapper;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.model.Animal;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface AnimalMapper {
    Animal toEntity(AnimalRequestDto dto);
    AnimalResponseDto toResponse(Animal animal);
    List<AnimalResponseDto> toResponse(List<Animal> animais);
}