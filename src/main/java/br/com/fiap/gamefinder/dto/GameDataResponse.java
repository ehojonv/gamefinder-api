package br.com.fiap.gamefinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GameDataResponse(
        double rating,
        @JsonProperty("ratings_count")
        int ratingsCount,
        String released
        ) {
}
