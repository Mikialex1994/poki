package com.example.api.service;

import com.example.api.dto.PokemonDto;
import com.example.api.dto.PokemonResponse;

import java.util.List;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto,int id);
    void deletePokemon(int id);

}
