package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") int totalTemporadas,
        @JsonAlias("imdbRating") String avaliacao,
        @JsonAlias("Genre") String genero,
        @JsonAlias("Actors") String atores,
        @JsonAlias("Poster") String poster,
        @JsonAlias("Plot") String sinopse) {

}
// @JsonIgnoreProperties(ignoreUnknown = true)
// public record DadosSerie(@JsonProperty("Title") String titulo,
// @JsonProperty("totalSeasons")int totalTemporadas,
// @JsonProperty("imdbRating") String avaliacao) {

// }
