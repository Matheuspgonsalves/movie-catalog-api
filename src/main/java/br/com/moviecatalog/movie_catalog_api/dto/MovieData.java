package br.com.moviecatalog.movie_catalog_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MovieData(
        @JsonAlias("Title") String title,
        @JsonAlias("Year") String year,
        @JsonAlias("imdbRating") double rating,
        @JsonAlias("Released") String released
) {

    @Override
    public String toString() {
        return "MovieData\n\n" +
                "title = " + title + '\n' +
                "year = " + year + '\n' +
                "rating = " + rating + '\n' +
                "released = " + released + '\n';
    }
}
