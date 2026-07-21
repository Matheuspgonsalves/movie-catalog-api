package br.com.moviecatalog.movie_catalog_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieData(
        @JsonAlias("Title") String title,
        @JsonAlias("Year") String year,
        @JsonAlias("imdbRating") String rating,
        @JsonAlias("Released") String released
) {
}
