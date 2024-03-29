package com.artamonov.restaurantmenuvoting.repository;

import com.artamonov.restaurantmenuvoting.model.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    Dish findByIdAndRestaurantId(int id, int restaurantId);

    List<Dish> findAllByRestaurantIdAndPublished(int restaurantId, LocalDate published, Sort sort);

    List<Dish> findAllByRestaurantId(int restaurantId, Sort sort);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d JOIN FETCH Restaurant r WHERE d.id=:id AND r.id=:restaurantId")
    Dish getWithRestaurant(@Param("id") int id, @Param("restaurantId") int restaurantId);

}
