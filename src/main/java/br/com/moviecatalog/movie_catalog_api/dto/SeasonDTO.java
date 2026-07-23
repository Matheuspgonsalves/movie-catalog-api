package br.com.moviecatalog.movie_catalog_api.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonDTO (
        @JsonAlias("Season") Integer season,
        @JsonAlias("Episodes") List<EpisodeDTO> episodes
        ) {
}
