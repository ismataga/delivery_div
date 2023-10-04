package com.example.delivery_div.repository;


import com.example.delivery_div.models.User;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //    @Query("SELECT u FROM User u join Role r where r.name = 'ROLE_CUSTOMER'")
    @Query(value = """
            select users.*
            from delivery_div.users
                     inner join delivery_div.user_role  on users.id = user_role.user_id
                     inner join delivery_div.role  on role.id = user_role.role_id
            where role.name = 'ROLE_CUSTOMER'
                                    """, nativeQuery = true)
    List<User> getAllCustomers();

    Optional<User> findByEmail(String email);


}
