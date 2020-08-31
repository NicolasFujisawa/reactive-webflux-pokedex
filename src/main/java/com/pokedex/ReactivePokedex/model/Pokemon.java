package com.pokedex.ReactivePokedex.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pokemon {

    @Id
    private String id;

    private String nome;
    private String categoria;
    private String habilidade;
    private Double peso;

    public Pokemon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    

    public Pokemon(String id, String nome, String categoria, String habilidade, Double peso) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.habilidade = habilidade;
        this.peso = peso;
    }
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", habilidade='" + getHabilidade() + "'" +
            ", peso='" + getPeso() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pokemon)) {
            return false;
        }
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(nome, pokemon.nome) && Objects.equals(categoria, pokemon.categoria) && Objects.equals(habilidade, pokemon.habilidade) && Objects.equals(peso, pokemon.peso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, categoria, habilidade, peso);
    }
    
}