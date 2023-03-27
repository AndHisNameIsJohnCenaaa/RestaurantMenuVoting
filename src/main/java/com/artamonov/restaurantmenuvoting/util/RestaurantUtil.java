package com.artamonov.restaurantmenuvoting.util;

import com.artamonov.restaurantmenuvoting.model.Restaurant;
import com.artamonov.restaurantmenuvoting.to.RestaurantTo;

import java.util.List;

public class RestaurantUtil {
    public static List<RestaurantTo> getRestaurantTos(List<Restaurant> restaurantsWithDishesAndVotes) {
        return restaurantsWithDishesAndVotes.stream()
                .map(r -> new RestaurantTo(r.id(), r.getName(), r.getVotes(), r.getDishes()))
                .toList();
    }
}
