package com.artamonov.restaurantmenuvoting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "cast"}, name = "vote_unique_user_cast_idx")})
@NoArgsConstructor
@Getter
@Setter
public class Vote extends AbstractBaseEntity {

    @Column(name = "cast")
    @NotNull
    private LocalDate cast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Vote(LocalDate cast, User user, Restaurant restaurant) {
        this(null, cast, user, restaurant);
    }

    public Vote(Integer id, LocalDate cast, User user, Restaurant restaurant) {
        super(id);
        this.cast = cast;
        this.user = user;
        this.restaurant = restaurant;
    }

}
