package com.artamonov.restaurantmenuvoting.controller.vote;

import com.artamonov.restaurantmenuvoting.model.Vote;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/votes";

    @PostMapping("/{restaurantId}")
    public Vote vote(@PathVariable int restaurantId) {
        return null;
    }
}
