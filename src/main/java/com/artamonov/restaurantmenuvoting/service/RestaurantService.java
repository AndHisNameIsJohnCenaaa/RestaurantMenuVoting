package com.artamonov.restaurantmenuvoting.service;

import com.artamonov.restaurantmenuvoting.model.Restaurant;
import com.artamonov.restaurantmenuvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.artamonov.restaurantmenuvoting.util.ValidationUtil.checkNotFound;
import static com.artamonov.restaurantmenuvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class RestaurantService {

    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final RestaurantRepository crudRepository;

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return crudRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return crudRepository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(crudRepository.delete(id) != 0, id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(crudRepository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getByNameStartingWith(String name) {
        return crudRepository.findByNameStartingWith(name);
    }

    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

}
