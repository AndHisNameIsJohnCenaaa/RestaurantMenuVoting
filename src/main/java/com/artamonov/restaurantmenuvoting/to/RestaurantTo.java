package com.artamonov.restaurantmenuvoting.to;

import com.artamonov.restaurantmenuvoting.model.Dish;
import com.artamonov.restaurantmenuvoting.model.Vote;
import lombok.*;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
public class RestaurantTo {
    private int id;
    private String name;
    private int rankingForDay;
    private List<Vote> votesForDay;
    private List<Dish> dishedForDay;

    public RestaurantTo(int id, String name, Collection<Vote> votes, Collection<Dish> dishes) {
        this.id = id;
        this.name = name;
        this.votesForDay = List.copyOf(votes);
        this.dishedForDay = List.copyOf(dishes);
        this.rankingForDay = votesForDay.size();
    }

}
