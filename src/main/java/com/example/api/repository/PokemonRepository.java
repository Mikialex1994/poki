package com.example.api.repository;

import com.example.api.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {

    Optional<Pokemon> findByType(String type);

}
