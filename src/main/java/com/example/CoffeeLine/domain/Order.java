package com.example.CoffeeLine.domain;

import com.example.CoffeeLine.common.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_cost", nullable = false)
    private double totalCost;

    @Column(name = "status", columnDefinition = "VARCHAR", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OrderItem> items;

}
