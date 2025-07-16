# dsy1103_orders
Demo del microservicio ```orders``` de la app web de un e-commerce. Los microservicios disponibles son:
- https://github.com/toopazo/dsy1103_orders
- https://github.com/toopazo/dsy1103_products

## Framework y librerías
La app web está basada en el framework Spring Boot (Java). Se hace uso del decorador @RestController (https://spring.io/guides/gs/rest-service) para construir una API Rest.

Se usan las librerías:
- JPA: https://spring.io/projects/spring-data-jpa
- Lombok: https://projectlombok.org/
- Datafaker: https://www.datafaker.net/
- OpenApi OAS: https://springdoc.org/
- HATEOAS: https://spring.io/projects/spring-hateoas
- Junit: https://docs.junit.org/current/user-guide/

## Estructura de archivos fuente

La estructura del código se puede explorar con
```bash
tree src/main/java/cl/dsy1103/orders/  -L 2
```
```bash
src/main/java/cl/dsy1103/orders/
├── assembler
│   └── OrderModelAssembler.java
├── controller
│   └── OrderController.java
├── DataLoader.java
├── model
│   └── Order.java
├── OrdersApplication.java
├── repository
│   └── OrderRepository.java
└── services
    └── OrderService.java
```

## Breve ejemplo de respuesta

Un ejemplo de respuesta de la API con el método GET en ```http://localhost:8082/api/v1/orders``` es:
```json
{
    "_embedded": {
        "orderList": [
            {
                "id": 11,
                "tableId": 0,
                "menuId": 3,
                "menuCount": 3,
                "createdAt": "2025-07-15T15:05:30.259044",
                "_links": {
                    "self": {
                        "href": "http://localhost:8082/api/v1/orders/11"
                    },
                    "orders": {
                        "href": "http://localhost:8082/api/v1/orders"
                    },
                    "delete": {
                        "href": "http://localhost:8082/api/v1/orders/11"
                    }
                }
            },
            {
                "id": 12,
                "tableId": 1,
                "menuId": 14,
                "menuCount": 1,
                "createdAt": "2025-07-16T15:05:30.671559",
                "_links": {
                    "self": {
                        "href": "http://localhost:8082/api/v1/orders/12"
                    },
                    "orders": {
                        "href": "http://localhost:8082/api/v1/orders"
                    },
                    "delete": {
                        "href": "http://localhost:8082/api/v1/orders/12"
                    }
                }
            },
    

    "_links": {
        "self": {
            "href": "http://localhost:8082/api/v1/orders"
        }
    }
}    
```