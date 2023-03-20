package com.artamonov.restaurantmenuvoting.repository;

import com.artamonov.restaurantmenuvoting.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DataJpaDishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> getAllByRestaurantIdAndPublished(int restaurantId, LocalDate published);

    Dish getByIdAndRestaurantId(int id, int restaurantId);
}
