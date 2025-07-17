package com.animaisparaadocao.animaisparaadocao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private Boolean disponivel;
    private LocalDate dataDeResgate;

    public Animal(String nome, String especie, String raca, int idade, Boolean disponivel, LocalDate dataDeResgate) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.disponivel = disponivel;
        this.dataDeResgate = dataDeResgate;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public int getIdade() {
        return idade;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public LocalDate getDataDeResgate() {
        return dataDeResgate;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setDataDeResgate(LocalDate dataDeResgate) {
        this.dataDeResgate = dataDeResgate;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", raca='" + raca + '\'' +
                ", idade=" + idade +
                ", disponivel=" + disponivel +
                ", dataDeResgate=" + dataDeResgate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return idade == animal.idade && Objects.equals(nome, animal.nome) && Objects.equals(especie, animal.especie) && Objects.equals(raca, animal.raca) && Objects.equals(dataDeResgate, animal.dataDeResgate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, especie, raca, idade, dataDeResgate);
    }
}