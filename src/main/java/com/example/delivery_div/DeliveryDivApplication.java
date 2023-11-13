package com.example.delivery_div;

import com.example.delivery_div.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;


@EnableScheduling
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
@RequiredArgsConstructor
public class DeliveryDivApplication implements CommandLineRunner {


    private final EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {

        SpringApplication.run(DeliveryDivApplication.class, args);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void run(String... args) throws Exception {
        call(1l);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            EntityManager entityManager = entityManagerFactory.createEntityManager();


            System.out.println(Thread.currentThread().getName());
            try {
                entityManager.getTransaction().begin();

                User user = entityManager.find(User.class, 1l);
            call(1l);
                System.out.println(user.getName());
                entityManager.getTransaction().commit();
            }catch (SQLException ex) {
                entityManager.getTransaction().rollback();
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public void call(Long id)  throws SQLException{//lazy

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(runnable);
            thread.start();

        }


    }

}
