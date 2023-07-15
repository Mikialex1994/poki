package com.example.api.repository;

import com.example.api.model.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void ReviewRepository_SaveAll_ReturnSaveReview(){

        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

       Review review1 = reviewRepository.save(review);

        Assertions.assertThat(review1).isNotNull();
        Assertions.assertThat(review1.getId()).isGreaterThan(0);

    }

    @Test
    public void ReviewRepository_GetAll_ReturnsMoreThanOneReview(){

        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        Review review1 = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review1);

        List<Review> review2 = reviewRepository.findAll();

        Assertions.assertThat(review2).isNotNull();
        Assertions.assertThat(review2.size()).isGreaterThan(1);

    }

    @Test
    public void ReviewRepository_FindById_ReturnSavedReview(){

        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);

       Review review1 = reviewRepository.findById(review.getId()).get();

       Assertions.assertThat(review1).isNotNull();

    }

    @Test
    public void ReviewRepository_UpdateReview_ReturnReview(){

        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);

        Review review1 = reviewRepository.findById(review.getId()).get();

        review1.setTitle("title");
        review1.setContent("content");
        review1.setStars(5);

        Review review2 = reviewRepository.save(review1);

        Assertions.assertThat(review2).isNotNull();
        Assertions.assertThat(review2.getContent()).isNotNull();
        Assertions.assertThat(review2.getTitle()).isNotNull();

    }

    @Test
    public void ReviewRepository_DeleteReview_ReturnIsEmpty(){

        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);

       reviewRepository.deleteById(review.getId());

       Optional<Review> review1 = reviewRepository.findById(review.getId());

       Assertions.assertThat(review1).isEmpty();

    }


}
