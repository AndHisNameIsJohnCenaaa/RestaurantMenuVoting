package com.artamonov.restaurantmenuvoting.repository;

import com.artamonov.restaurantmenuvoting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaVoteRepository extends JpaRepository<Vote, Integer> {
}
