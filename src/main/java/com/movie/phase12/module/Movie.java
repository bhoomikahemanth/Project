package com.movie.phase12.module;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class Movie {

private int movie_id;
private String movie_title;
private String movie_summery;
private String movie_duration;
private String movie_genre;
private String movie_release_date;
private String movie_cover_image;
private String movie_trailer;
private  int avg_rate;
}
