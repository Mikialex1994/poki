package com.example.api.service;

import com.example.api.dto.PokemonDto;
import com.example.api.dto.PokemonResponse;
import com.example.api.model.Pokemon;
import com.example.api.repository.PokemonRepository;
import com.example.api.service.impl.PokemonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import static org.junit.jupiter.api.Assertions.assertAll;


import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    public void PokemonService_CreatePokemon_ReturnPokemonDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto pokemonDto1 = pokemonService.createPokemon(pokemonDto);

        Assertions.assertThat(pokemonDto1).isNotNull();

    }

    @Test
    public void PokemonService_GetAllPokemon_ReturnResponseDto(){

        PokemonResponse pokemonResponse = Mockito.mock(PokemonResponse.class);

        Page<Pokemon> pokemons = Mockito.mock(Page.class);

        when(pokemonRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pokemons);

        PokemonResponse pokemonResponse1 = pokemonService.getAllPokemon(1,10);

        Assertions.assertThat(pokemonResponse1).isNotNull();

    }

    @Test
    public void PokemonService_GetById_ReturnDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

        PokemonDto pokemonDto1 = pokemonService.getPokemonById(1);

        Assertions.assertThat(pokemonDto1).isNotNull();

    }

    @Test
    public void PokemonService_UpdatePokemon_ReturnPokemonDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        PokemonDto pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto pokemonDto1 = pokemonService.updatePokemon(pokemonDto,1);

        Assertions.assertThat(pokemonDto1).isNotNull();

    }

    @Test
    public void PokemonService_DeleteById_ReturnDto(){

        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

        assertAll(()-> pokemonService.deletePokemon(1));

    }




}
