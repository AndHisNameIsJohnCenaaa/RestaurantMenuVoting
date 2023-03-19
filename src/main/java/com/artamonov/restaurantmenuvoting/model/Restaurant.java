package com.artamonov.restaurantmenuvoting.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_unique_name_idx")})
@NoArgsConstructor
@Getter
@Setter
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "published", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate published;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("cast DESC")
    private List<Vote> votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("published DESC")
    private List<Dish> dishes;

    public Restaurant(String name, LocalDate published) {
        this(null, name, published);
    }

    public Restaurant(Integer id, String name, LocalDate published) {
        super(id, name);
        this.published = published;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", published=" + published +
                '}';
    }
}
