package cl.dsy1103.orders.services;

import java.util.List;

import jakarta.transaction.Transactional;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.dsy1103.orders.model.Order;
import cl.dsy1103.orders.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) {
        Order newOrder = orderRepository.save(order);
        return newOrder;
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Order order) {
        Order oldOrder = orderRepository.findById(order.getId()).orElse(null);
        if (oldOrder == null) {
            // throw new OrderNotFoundException("Order not found with id: " + id);
            return null;
        }
        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }

    public Order deleteOrder(int id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order ==  null){
            return null;
        }
        orderRepository.deleteById(id);
        return order;
    }

}
