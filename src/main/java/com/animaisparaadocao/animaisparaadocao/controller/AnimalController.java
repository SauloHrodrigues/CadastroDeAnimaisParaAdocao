package com.animaisparaadocao.animaisparaadocao.controller;

import com.animaisparaadocao.animaisparaadocao.dto.AnimalAtualizarDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalRequestDto;
import com.animaisparaadocao.animaisparaadocao.dto.AnimalResponseDto;
import com.animaisparaadocao.animaisparaadocao.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/animais")
public class AnimalController implements AnimalControlerSwagerInterface {

    private final AnimalService service;

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDto> cadastrarNovoAnimal(@Valid @RequestBody AnimalRequestDto dto) {
        var novoAnimal = service.cadastrarNovoAnimal(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimal);
    }

    @GetMapping
    public ResponseEntity<Page<AnimalResponseDto>> retornarTodosAnimaisCadastrados(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var resposta = service.retornaTodosAnimaisCadastrados(pageable);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> buscarUmAnimalPorId(@PathVariable Long id) {
        var resposta = service.buscarAnimalNoBancoPorId(id);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> atualizarUmAnimal(@PathVariable Long id, @Valid @RequestBody AnimalAtualizarDto atualizacoes) {
        var animalAtualizado = service.atualizarDadosDoAnimal(id, atualizacoes);
        return ResponseEntity.ok(animalAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        service.deletarAnimalDoBanco(id);
        return ResponseEntity.noContent().build();
    }
}