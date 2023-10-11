package com.example.delivery_div.repository;


import com.example.delivery_div.models.Cart;
import com.example.delivery_div.models.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            SELECT COUNT(*) AS order_count
           FROM delivery_div.order  
           GROUP BY DATE(delivery_div."order"."createdDate");
                                                 """, nativeQuery = true)
    Integer getOrdersCount();

    @Query(value = "SELECT SUM(cart.total_amount) AS total_income FROM cart WHERE DATE(cart.created_date) = :createdDate", nativeQuery = true)
    Integer getOrdersDailySum(@Param("createdDate") LocalDate createdDate);

    @Query(value = "SELECT SUM(cart.total_amount) AS total_income FROM cart WHERE cart.created_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    Integer getOrdersMonthlySum(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);





}
