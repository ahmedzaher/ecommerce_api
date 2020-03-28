package com.ecommerce.ecommerce_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float price;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    private boolean available;

    @Column(name = "creation_time", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationTime;

    @Column(name = "last_updated_time")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
}
