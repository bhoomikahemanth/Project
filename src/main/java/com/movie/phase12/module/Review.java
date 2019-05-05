package com.movie.phase12.module;

import lombok.Data;

@Data
public class Review {

    private int review_id;
    private int user_id;
    private int movie_id;
    private String review_comment;
    private int review_rating;
    private String review_date;
}
