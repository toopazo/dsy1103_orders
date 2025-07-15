package cl.dsy1103.orders.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.dsy1103.orders.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // List<Order> findAll(); // This method is inherited from JpaRepository

    // List<Order> findByCreatedAt(LocalDateTime createdAt); // This method is
    // inherited from JpaRepository

    // @Query("SELECT m FROM dinner_order m WHERE m.menu_id = 'id'")
    // List<Order> findByMenuId(int id); // Custom query to find menus by name
    // pattern

    // @Query(value = "select * from dinner_order", nativeQuery = true)
    // List<Order> findAllOrders(); // Custom query to find all menus
}