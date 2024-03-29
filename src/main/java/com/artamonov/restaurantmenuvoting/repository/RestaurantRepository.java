package com.artamonov.restaurantmenuvoting.repository;

import com.artamonov.restaurantmenuvoting.model.Restaurant;
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
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    List<Restaurant> findByNameStartingWith(String name);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH User u LEFT JOIN FETCH Vote v where v.cast=:cast")
    List<Restaurant> getAllWithDishesAndVotesByVoteCast(@Param("cast") LocalDate cast);
}
