package com.artamonov.restaurantmenuvoting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "published"}, name = "dish_unique_user_published_idx")})
@NoArgsConstructor
@Getter
@Setter
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    @Range(min = 100)
    private int price;

    @Column(name = "published", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    private LocalDate published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Dish(String name, int calories) {
        this(null, name, calories);
    }

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", published=" + published +
                '}';
    }
}
