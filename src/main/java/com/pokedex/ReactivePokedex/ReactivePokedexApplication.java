package com.pokedex.ReactivePokedex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import reactor.core.publisher.Flux;

import com.pokedex.ReactivePokedex.repository.PokedexRepository;
import com.pokedex.ReactivePokedex.model.Pokemon;

@SpringBootApplication
public class ReactivePokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivePokedexApplication.class, args);
		System.out.println("Hello reactive webflux!");

	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations,
						   PokedexRepository repository) {
		return args -> {
			Flux<Pokemon> pokedexFlux = Flux.just(
					new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
					new Pokemon(null, "Charizard", "Fogo", "Blaze", 90.05),
					new Pokemon(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
					new Pokemon(null, "Blastoide", "Marisco", "Torrente", 6.5)
			).flatMap(repository::save);

			pokedexFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}

}
