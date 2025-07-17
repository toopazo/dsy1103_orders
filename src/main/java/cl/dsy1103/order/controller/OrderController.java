package cl.dsy1103.order.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cl.dsy1103.order.assembler.OrderModelAssembler;
import cl.dsy1103.order.model.Order;
import cl.dsy1103.order.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// @Autowired
	// private OrderController orderController;

	@Autowired
    private OrderModelAssembler assembler;

	@GetMapping(value = "", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity<CollectionModel<EntityModel<Order>>> getOrders() {
		List<EntityModel<Order>> orders = orderService.getOrders().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
		
		CollectionModel<EntityModel<Order>> collection = CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).getOrders()).withSelfRel());

		return new ResponseEntity<>(collection, HttpStatus.OK);
	}

	// Create POST
	@PostMapping("")
	public ResponseEntity<EntityModel<Order>> postOrder(@RequestBody Order order) {
		// int id = orderService.getOrders().size() + 1;
		// int id = ++counter;
		// order.setId(id);
		order.setCreatedAt(LocalDateTime.now());
		Order newOrder = orderService.addOrder(order);

		// return ResponseEntity
        //         .created(linkTo(methodOn(OrderController.class).getOrderById(order.getId())).toUri())
        //         .body(assembler.toModel(order));

		EntityModel<Order> entityModel = assembler.toModel(newOrder);
		// URI location = linkTo(methodOn(OrderController.class).getOrderById(order.getId())).toUri();
		// URI location = linkTo(orderController.getOrderById(newOrder.getId())).toUri();
		// return ResponseEntity.created(location).body(entityModel);
		return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
	}

	// Read GET
	@GetMapping("{id}")
	public ResponseEntity<EntityModel<Order>> getOrderById(@PathVariable("id") int id) {
		Order order = orderService.getOrderById(id);
		if (order == null) {
			Order nullOrder = new Order();
			EntityModel<Order> entityModel = assembler.toModel(nullOrder);
			return new ResponseEntity<>(entityModel, HttpStatus.NOT_FOUND);
			// return ResponseEntity.notFound().build();
		}
		EntityModel<Order> entityModel = assembler.toModel(order);
		return ResponseEntity.ok(entityModel);
	}

	// Update PUT
	@PutMapping("{id}")
	public ResponseEntity<EntityModel<Order>> putOrderById(@RequestBody Order order) {
		Order updatedOrder = orderService.updateOrder(order);
		EntityModel<Order> entityModel = assembler.toModel(updatedOrder);
		return ResponseEntity.ok(entityModel);
	}

	// Delete DELETE
	@DeleteMapping("{id}")
	public ResponseEntity<EntityModel<Order>> deleteOrderById(@PathVariable("id") int id) {
		Order deletedOrder = orderService.deleteOrder(id);
		if (deletedOrder ==  null){
			Order nullOrder = new Order();
			EntityModel<Order> entityModel = assembler.toModel(nullOrder);
			return new ResponseEntity<>(entityModel, HttpStatus.NOT_FOUND);
			// return ResponseEntity.notFound().build();
        }
		EntityModel<Order> entityModel = assembler.toModel(deletedOrder);
		return ResponseEntity.ok(entityModel);
	}

}
