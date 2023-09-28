package com.example.delivery_div.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
    private Long id;
    @Column(length = 30)
    private String name;
    private String surname;
    @Past(message = "BirtDate must be past")
    private LocalDate birthDate;
    @Column(unique = true)
    @Pattern(regexp = "[0-9]{3}+[0-9]{3}+[0-9]{4}")
    private String email;
    private String password;
    @Pattern(regexp = "[0-9]{3}+[0-9]{3}+[0-9]{4}")
    private Integer phoneNumber;


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    @ToString.Exclude
    private Set<Role> role;

    private boolean isEnable;


}
