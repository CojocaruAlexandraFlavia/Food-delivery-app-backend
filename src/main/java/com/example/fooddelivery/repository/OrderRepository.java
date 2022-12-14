package com.example.fooddelivery.repository;

import com.example.fooddelivery.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying(clearAutomatically = true)
    @Query("DELETE from Order q WHERE q.id = ?1")
    void deleteById(@NotNull Long id);

    Optional<Order> findByStatus(Order status);
}