package com.pokedex.ReactivePokedex;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;

public class TesteMono {
    
    @Test
    public void testeMonoVazio() {
        Mono<String> mono = Mono.empty();
    }

    @Test
    public void testeMonoString() {
        Mono<String> mono = Mono.just("Pokemon");
        mono.subscribe(System.out::println);
    }

    @Test
    public void testeMonoInteger() {
        Mono<Integer> mono = Mono.just(12);
        mono.subscribe(System.out::println);
    }

    @Test
    public void testeMonoExcessaoRunTime() {
        Mono<String> mono = Mono.error(new RuntimeException("Ocorreu algum erro"));
    }

}