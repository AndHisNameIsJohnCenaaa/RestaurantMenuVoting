package com.artamonov.restaurantmenuvoting.service;

import com.artamonov.restaurantmenuvoting.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteService {

    private VoteRepository voteRepository;
}
