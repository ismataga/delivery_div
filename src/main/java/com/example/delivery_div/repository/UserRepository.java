package com.example.delivery_div.repository;


import com.example.delivery_div.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);


    //    @Query("SELECT u FROM User u join Role r where r.name = 'ROLE_CUSTOMER'")
    @Query(value = """
            select users.*
            from delivery_div.users
                     inner join delivery_div.users_roles  on users.id = users_roles.user_id
                     inner join delivery_div.roles  on roles.id = users_roles.roles_id
            where roles.name = 'ROLE_CUSTOMER'
                                    """, nativeQuery = true)
    List<User> getAllCustomers();

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String email);

    Optional<User> findByUuid(UUID uuid);

    @Query(value = """
            SELECT COUNT(u.id) AS customer_count
            FROM delivery_div.users u
            INNER JOIN delivery_div.users_roles ur ON u.id = ur.user_id
            INNER JOIN delivery_div.roles r ON ur.roles_id = r.id
            WHERE r.name = 'ROLE_CUSTOMER'
                                    """, nativeQuery = true)
    Integer getCustomersCount();

    @Query(value = """
            SELECT COUNT(u.id) AS drivers_count
            FROM delivery_div.users u
            INNER JOIN delivery_div.users_roles ur ON u.id = ur.user_id
            INNER JOIN delivery_div.roles r ON ur.roles_id = r.id
            WHERE r.name = 'ROLE_DRIVERS'
                                    """, nativeQuery = true)
    Integer getDriversCount();

    @Query(value = """

             SELECT COUNT(o.id) AS orders_count
            FROM delivery_div.order o
                                     """, nativeQuery = true)
    Integer getOrdersCount();



}
