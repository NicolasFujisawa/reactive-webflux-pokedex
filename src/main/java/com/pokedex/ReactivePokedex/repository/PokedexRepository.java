package com.pokedex.ReactivePokedex.repository;

import com.pokedex.ReactivePokedex.model.Pokemon;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String> {
    
}