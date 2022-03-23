package com.booking.movieGateway;

import com.booking.movieGateway.exceptions.FormatException;
import com.booking.movieGateway.models.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MovieService {
    private final MovieGateway movieGateway;

    @Autowired
    public MovieService(MovieGateway movieGateway) {
        this.movieGateway = movieGateway;
    }

    public List<MovieResponse> getAllMovies() throws IOException, FormatException {
        return movieGateway.getAllMovies();
    }
}
