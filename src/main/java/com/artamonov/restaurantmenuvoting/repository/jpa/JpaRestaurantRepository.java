package com.artamonov.restaurantmenuvoting.repository.jpa;

import com.artamonov.restaurantmenuvoting.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
