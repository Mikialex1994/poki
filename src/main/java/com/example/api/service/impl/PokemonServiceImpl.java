package com.example.api.service.impl;

import com.example.api.dto.PokemonDto;
import com.example.api.dto.PokemonResponse;
import com.example.api.exeptions.PokemonNotFoundException;
import com.example.api.model.Pokemon;
import com.example.api.repository.PokemonRepository;
import com.example.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {

        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon pokemon1 = pokemonRepository.save(pokemon);

        PokemonDto pokemonDto1 = new PokemonDto();
        pokemonDto1.setId(pokemon1.getId());
        pokemonDto1.setName(pokemon1.getName());
        pokemonDto1.setType(pokemon1.getType());

        return pokemonDto1;

    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);

        Page<Pokemon> pokemon = pokemonRepository.findAll(pageable);

        List<Pokemon> listOfPokemon = pokemon.getContent();

        List<PokemonDto> content = listOfPokemon.stream().map(this::mapToDto).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemon.getNumber());
        pokemonResponse.setPageSize(pokemon.getSize());
        pokemonResponse.setTotalElements(pokemon.getTotalElements());
        pokemonResponse.setTotalPages(pokemon.getTotalPages());
        pokemonResponse.setLast(pokemon.isLast());

        return pokemonResponse;
    }

    @Override
    public PokemonDto getPokemonById(int id) {

        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon could Not Be Found!!"));
        return mapToDto(pokemon);

    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {

        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon could Not be Updated"));

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon updatePokemon = pokemonRepository.save(pokemon);

        return mapToDto(updatePokemon);
    }

    @Override
    public void deletePokemon(int id) {

        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon Could Not Be deleted"));
        pokemonRepository.delete(pokemon);

    }

    private PokemonDto mapToDto(Pokemon pokemon){

        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;

    }

    private Pokemon mapToPokemon(PokemonDto pokemonDto){

        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;

    }


}
