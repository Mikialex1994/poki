package com.example.api.service;

import com.example.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

ReviewDto crateReview(int pokemonId, ReviewDto reviewDto);
List<ReviewDto> getReviewsByPokemon(int id);
ReviewDto getReviewById(int reviewId, int pokemonId);
ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto);
void deleteReviewId(int pokemonId, int reviewId);
}
