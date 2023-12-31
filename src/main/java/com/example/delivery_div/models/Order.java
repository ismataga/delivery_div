package com.example.delivery_div.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(schema = "delivery_div"  )
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String place;
    @OneToOne
    private Cart card;
    @OneToOne
    private Driver driver;
    private boolean  status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
