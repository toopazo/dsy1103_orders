package cl.dsy1103.orders.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import cl.dsy1103.orders.model.Order;
import cl.dsy1103.orders.controller.OrderController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {

    // public OrderModelAssembler() {
    //     super(OrderController.class);
    // }

    @Override
    @NonNull
    public EntityModel<Order> toModel(@NonNull Order order) {
        return EntityModel.of(order,
                linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getOrders()).withRel("orders"),
                linkTo(methodOn(OrderController.class).deleteOrderById(order.getId())).withRel("delete")
                );
                
    }
}