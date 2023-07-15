package com.example.api.service.impl;

import com.example.api.dto.ReviewDto;
import com.example.api.exeptions.PokemonNotFoundException;
import com.example.api.exeptions.ReviewNotFoundException;
import com.example.api.model.Pokemon;
import com.example.api.model.Review;
import com.example.api.repository.PokemonRepository;
import com.example.api.repository.ReviewRepository;
import com.example.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewDto crateReview(int pokemonId, ReviewDto reviewDto) {

        Review review = mapToEntity(reviewDto);

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));

        review.setPokemon(pokemon);

        Review newReview = reviewRepository.save(review);

        return mapTODto(newReview);

    }

    @Override
    public List<ReviewDto> getReviewsByPokemon(int id) {

        List<Review> reviews = reviewRepository.findByPokemonId(id);

        return reviews.stream().map(review -> mapTODto(review)).collect(Collectors.toList());

    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found "));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated pokemon not found"));

        if(review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This review does not belong to a pokemon");

        }
        return mapTODto(review);

    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found "));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated pokemon not found"));

        if(review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This review does not belong to a pokemon");

        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updateReview = reviewRepository.save(review);

        return mapTODto(updateReview);
    }

    @Override
    public void deleteReviewId(int pokemonId, int reviewId) {

        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon not found"));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated pokemon not found"));

        if(review.getPokemon().getId() != pokemon.getId()){

            throw new ReviewNotFoundException("This review does not belong to a pokemon");

        }

            reviewRepository.delete(review);

    }

    private ReviewDto mapTODto(Review review){

        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());

        return reviewDto;

    }

    private Review mapToEntity(ReviewDto reviewDto){

        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        return review;

    }

}
