package cl.dsy1103.order;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import cl.dsy1103.order.model.Order;
import cl.dsy1103.order.repository.OrderRepository;

import java.time.LocalDateTime;
// import java.util.Random;

// Ver detalles de Profile en Spring Boot en la siguiente URL: https://docs.spring.io/spring-boot/reference/features/profiles.html
// @Profile("dev")
@Profile("do_not_run")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        // Random random = new Random();

        // Generar libros
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setId(i + 1);
            order.setTableId(i);
            order.setMenuId(faker.number().numberBetween(1, 20));
            order.setMenuCount(faker.number().numberBetween(1, 5));
            LocalDateTime localDateTime = LocalDateTime.now();
            order.setCreatedAt(localDateTime.plusDays(i));

            System.out.println("Order generated: " + order);

            // Guardar el objeto en la base de datos
            try {
                orderRepository.save(order);
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                // Handle the exception
                System.err.println("Data integrity violation occurred: " + e.getMessage());
            }

        }
    }
}
