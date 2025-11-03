package com.example.CoffeeLine.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
