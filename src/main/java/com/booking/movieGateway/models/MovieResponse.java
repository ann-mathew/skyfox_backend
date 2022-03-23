package com.booking.movieGateway.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {

    @JsonProperty("imdbID")
    private String id;

    @JsonProperty("Title")
    private String name;

    public MovieResponse() {
    }

    public MovieResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieResponse movie = (MovieResponse) o;
        return id.equals(movie.id) &&
                name.equals(movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
