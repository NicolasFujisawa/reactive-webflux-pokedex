package com.pokedex.ReactivePokedex.controller;

import java.time.Duration;

import com.pokedex.ReactivePokedex.model.Pokemon;
import com.pokedex.ReactivePokedex.model.PokemonEvent;
import com.pokedex.ReactivePokedex.repository.PokedexRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {
    
    @Autowired
    private PokedexRepository repository;

    @GetMapping
    public Flux<Pokemon> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> get(@PathVariable String id) {
        return repository.findById(id)
                .map(pokemonExistente -> ResponseEntity.ok(pokemonExistente))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> add(@RequestBody Pokemon pokemon) {
        return repository.save(pokemon);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Pokemon>> update(@PathVariable(value="id") String id, 
                                                @RequestBody Pokemon pokemon) {
        return repository.findById(id)
                .flatMap(existingPokemon -> {
                    existingPokemon.setNome(pokemon.getNome());
                    existingPokemon.setCategoria(pokemon.getCategoria());
                    existingPokemon.setHabilidade(pokemon.getHabilidade());
                    existingPokemon.setPeso(pokemon.getPeso());
                    return repository.save(existingPokemon);
                })
                .map(updatePokemon -> ResponseEntity.ok(updatePokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value="id") String id) {
        return repository.findById(id)
                .flatMap(existingPokemon -> 
                    repository.delete(existingPokemon)
                    .then(Mono.just(ResponseEntity.ok().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> events() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(mapper -> new PokemonEvent(mapper, "Evento de Pokemon"));
    }
}