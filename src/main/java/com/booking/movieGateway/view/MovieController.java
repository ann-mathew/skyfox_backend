package com.booking.movieGateway.view;

import com.booking.handlers.models.ErrorResponse;
import com.booking.movieGateway.MovieService;
import com.booking.movieGateway.exceptions.FormatException;
import com.booking.movieGateway.models.MovieResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Api(tags = "MovieSchedule")
@RestController
@RequestMapping("/movieSchedule")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @ApiOperation(value = "Fetch movies")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Fetched movies successfully"),
            @ApiResponse(code = 500, message = "Something failed in the server", response = ErrorResponse.class)
    })
    public List<MovieResponse> getAllMovies() throws IOException, FormatException {
        return movieService.getAllMovies();
    }
}
