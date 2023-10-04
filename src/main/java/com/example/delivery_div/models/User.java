package com.example.delivery_div.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Set;

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
@Table(schema = "delivery_div", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String name;
    private String surname;
    @Past(message = "BirtDate must be past")
    private LocalDate birthdate;
    @Column(unique = true)
//    @Pattern(regexp = "[0-9]{3}+[0-9]{3}+[0-9]{4}")
    private String email;
    private String password;
//    @Pattern(regexp = "[0-9]{3}[0-9]{3}[0-9]{4}")
    private Integer phoneNumber;


    @ManyToMany
    @JoinTable(
            schema = "delivery_div",
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @ToString.Exclude
    private Set<Role> role;

    private Boolean active;


}



