package com.artamonov.restaurantmenuvoting.service;

import com.artamonov.restaurantmenuvoting.model.Dish;
import com.artamonov.restaurantmenuvoting.repository.DishRepository;
import com.artamonov.restaurantmenuvoting.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.artamonov.restaurantmenuvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class DishService {

    private static final Sort SORT_PUBLISHED_NAME = Sort.by(Sort.Direction.DESC, "published", "name");

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(save(dish, restaurantId), dish.id());
    }

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return save(dish, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(dishRepository.delete(id, restaurantId), id);
    }

    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(dishRepository.findByIdAndRestaurantId(id, restaurantId), id);
    }

    public Dish getWithRestaurant(int id, int restaurantId) {
        return checkNotFoundWithId(dishRepository.getWithRestaurant(id, restaurantId), id);
    }

    public List<Dish> getAll(int restaurantId) {
        return dishRepository.findAllByRestaurantId(restaurantId, SORT_PUBLISHED_NAME);
    }

    public List<Dish> getAllByPublished(int restaurantId, LocalDate published) {
        return dishRepository.findAllByRestaurantIdAndPublished(restaurantId, published, SORT_PUBLISHED_NAME);
    }

    private Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.id(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return dishRepository.save(dish);
    }
}
