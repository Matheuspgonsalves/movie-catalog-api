package br.com.moviecatalog.movie_catalog_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeDTO(
        @JsonAlias("Title") String title,
        @JsonAlias("Episode") Integer episode,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Released") String releaseDate
) {
    @Override
    public String toString() {
        return "EpisodeData\n\n" +
                "title = " + title + '\n' +
                "Episode = " + episode + '\n' +
                "imdbRating = " + rating + '\n' +
                "Released = " + releaseDate + '\n';
    }
}
