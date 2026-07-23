package br.com.moviecatalog.movie_catalog_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesDTO(
        @JsonAlias("Title") String title,
        @JsonAlias("totalSeasons") Integer totalSeasons,
        @JsonAlias("imdbRating") String rating
) {
    @Override
    public String toString() {
        return "SeriesData\n\n" +
                "title = " + title + '\n' +
                "totalSeasons = " + totalSeasons + '\n' +
                "imdbRating = " + rating + '\n';
    }
}
