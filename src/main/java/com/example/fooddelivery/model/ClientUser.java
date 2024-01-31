package com.example.fooddelivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("client_user")
@Getter
@Setter
public class ClientUser extends BaseUser {

    @OneToMany(mappedBy = "clientUser", cascade = CascadeType.ALL)
    private List<UserAddress> addresses;

    @OneToMany(mappedBy = "clientUser", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "clientUser", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "users_favorite_products",
            joinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_client_favorites"),
                            nullable = false,
                            updatable = false
                    )},
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id",
                            referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "fk_product_favorites"),
                            nullable = false,
                            updatable = false)})
    private Set<Product> products = new HashSet<>();

    public void addProductToFavoritesList(Product product) {
        products.add(product);
    }

    public void addAddress(UserAddress address) {
        addresses.add(address);
    }

    public void deleteAddress(UserAddress address) {
        addresses.remove(address);
    }

}
