package com.pokedex.ReactivePokedex;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;

public class TesteFlux {
    @Test
    public void testeFluxVazio() {
        Flux.empty();
    }

    @Test
    public void testeFluxString() {
        Flux<String> flux = Flux.just("Pokedex");
        flux.subscribe(System.out::println);
    }

    @Test
    public void testeFluxPokemons() {
        Flux<String> flux = Flux.just("Sharizard", "Pikachu", "Blastoide");
        flux.subscribe(System.out::println);
    }

    @Test
    public void testeFluxExcessaoRunTime() {
        Flux<String> flux = Flux.error(new RuntimeException("Ocorreu algum erro"));
    }
}