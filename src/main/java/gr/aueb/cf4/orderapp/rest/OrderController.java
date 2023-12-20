package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.OrderCreateDTO;
import gr.aueb.cf4.orderapp.dto.OrderReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.OrderUpdateDTO;
import gr.aueb.cf4.orderapp.dto.WishlistItemCreateDTO;
import gr.aueb.cf4.orderapp.service.IOrderService;
import gr.aueb.cf4.orderapp.service.exceptions.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final IOrderService orderService;

    @Autowired
    public OrderController(@Lazy IOrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create a new Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<OrderReadOnlyDTO> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        OrderReadOnlyDTO createdOrder = orderService.createOrder(orderCreateDTO);
        if (createdOrder != null) {
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all Orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderReadOnlyDTO.class)))})})
    @GetMapping
    public ResponseEntity<List<OrderReadOnlyDTO>> getAllOrders() {
        List<OrderReadOnlyDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Get an Order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderReadOnlyDTO> getOrderById(@PathVariable Long orderId) {
        OrderReadOnlyDTO order = orderService.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update an Order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderReadOnlyDTO> updateOrder(
            @PathVariable Long orderId,
            @RequestBody OrderUpdateDTO orderUpdateDTO) {
        OrderReadOnlyDTO updatedOrder = orderService.updateOrder(orderId, orderUpdateDTO);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete an Order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderReadOnlyDTO> deleteOrder(@PathVariable Long orderId) {
        OrderReadOnlyDTO deletedOrder = orderService.deleteOrder(orderId);
        if (deletedOrder != null) {
            return new ResponseEntity<>(deletedOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create an Order from Wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created from Wishlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PostMapping("/wishlist")
    public ResponseEntity<OrderReadOnlyDTO> createOrderFromWishlist(@RequestBody List<WishlistItemCreateDTO> wishlistItems) {
        try {
            OrderReadOnlyDTO createdOrder = orderService.createOrderFromWishlist(wishlistItems);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get all Orders for the currently logged-in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderReadOnlyDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @GetMapping("/current-user")
    public ResponseEntity<List<OrderReadOnlyDTO>> getAllOrdersForLoggedInUser() {
        try {
            List<OrderReadOnlyDTO> orders = orderService.getAllOrdersForLoggedInUser();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


