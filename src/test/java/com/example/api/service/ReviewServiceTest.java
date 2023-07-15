package com.example.api.service;

import com.example.api.dto.PokemonDto;
import com.example.api.dto.ReviewDto;
import com.example.api.model.Pokemon;
import com.example.api.model.Review;
import com.example.api.repository.PokemonRepository;
import com.example.api.repository.ReviewRepository;
import com.example.api.service.impl.ReviewServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Pokemon pokemon;
    private Review review;
    private ReviewDto reviewDto;
    private PokemonDto pokemonDto;

    @BeforeEach
    public void init(){

        pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonDto = PokemonDto.builder()
                .name("pikachu")
                .type("electric")
                .build();

        review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewDto = ReviewDto.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

    }

    @Test
    public void ReviewService_CreateReview_ReturnReviewDto(){

        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.of(pokemon));
        when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(review);

        ReviewDto saveReview = reviewService.crateReview(pokemon.getId(),reviewDto);

        Assertions.assertThat(saveReview).isNotNull();

    }

    @Test
    public void ReviewService_GetReviewsByPokemonId_ReturnReviewDto(){

        int reviewId = 1;

        when(reviewRepository.findByPokemonId(reviewId)).thenReturn(Arrays.asList(review));

        List<ReviewDto> pokemonReturn = reviewService.getReviewsByPokemon(reviewId);

        Assertions.assertThat(pokemonReturn).isNotNull();

    }

    @Test
    public void ReviewService_GetReviewById_ReturnReviewDto(){

        int reviewId = 1;
        int pokemonId = 1;

        review.setPokemon(pokemon);

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        ReviewDto reviewReturn = reviewService.getReviewById(reviewId,pokemonId);

        Assertions.assertThat(reviewReturn).isNotNull();

    }

    @Test
    public void ReviewService_UpdateReview_ReturnReviewDto(){

        int pokemonId = 1;
        int reviewId = 1;

        pokemon.setReviews(Arrays.asList(review));
        review.setPokemon(pokemon);

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(reviewRepository.save(review)).thenReturn(review);

        ReviewDto reviewDto1 = reviewService.updateReview(pokemonId,reviewId,reviewDto);

        Assertions.assertThat(reviewDto1).isNotNull();

    }

    @Test
    public void ReviewService_DeleteReviewById_ReturnReviewDto(){

        int pokemonId = 1;
        int reviewId = 1;

        pokemon.setReviews(Arrays.asList(review));
        review.setPokemon(pokemon);

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        assertAll(()-> reviewService.deleteReviewId(pokemonId,reviewId));

    }


}
