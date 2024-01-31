package com.example.fooddelivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seq_restaurant", sequenceName = "seq_restaurant")
    private Long id;

    private String name;
    private String phoneNumber;
    private Double rating;
    private Double deliveryTax;

    @ManyToOne
    @JoinColumn(
            name = "restaurant_manager_id",
            foreignKey = @ForeignKey(name = "fk_manager_restaurant")
    )
    private RestaurantManager restaurantManager;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Location> locations;

    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Review> reviews;

    @PreRemove
    public void preRemove() {
        this.locations.forEach(location -> location.setRestaurant(null));
        this.products.forEach(product -> product.setRestaurant(null));
        this.reviews.forEach(review -> review.setRestaurant(null));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;

        Restaurant that = (Restaurant) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
