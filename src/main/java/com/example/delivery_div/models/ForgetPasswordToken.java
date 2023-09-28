package com.example.delivery_div.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(schema = "delivery_div")
public class ForgetPasswordToken {
    @Id
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime confirmedAt;
    @OneToOne
    private User user;
    private boolean confirm;
}
