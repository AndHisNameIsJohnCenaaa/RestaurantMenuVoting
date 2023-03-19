package com.artamonov.restaurantmenuvoting.repository;

import com.artamonov.restaurantmenuvoting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaUserRepository extends JpaRepository<User, Integer> {
}
