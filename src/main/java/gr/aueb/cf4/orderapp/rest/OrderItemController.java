package gr.aueb.cf4.orderapp.rest;

import gr.aueb.cf4.orderapp.dto.OrderItemCreateDTO;
import gr.aueb.cf4.orderapp.dto.OrderItemReadOnlyDTO;
import gr.aueb.cf4.orderapp.dto.OrderItemUpdateDTO;
import gr.aueb.cf4.orderapp.service.IOrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final IOrderItemService orderItemService;

    @Autowired
    public OrderItemController(IOrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Operation(summary = "Create a new Order Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order Item created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<OrderItemReadOnlyDTO> createOrderItem(@RequestBody OrderItemCreateDTO orderItemCreateDTO) {
        OrderItemReadOnlyDTO createdOrderItem = orderItemService.createOrderItem(orderItemCreateDTO);
        if (createdOrderItem != null) {
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get all Order Items for a given Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Items Found",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderItemReadOnlyDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemReadOnlyDTO>> getAllOrderItemsByOrderId(@PathVariable Long orderId) {
        List<OrderItemReadOnlyDTO> orderItems = orderItemService.getAllOrderItemsByOrderId(orderId);
        if (!orderItems.isEmpty()) {
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get an Order Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Item Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Order Item not found",
                    content = @Content)})
    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemReadOnlyDTO> getOrderItemById(@PathVariable Long orderItemId) {
        OrderItemReadOnlyDTO orderItem = orderItemService.getOrderItemById(orderItemId);
        if (orderItem != null) {
            return new ResponseEntity<>(orderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update an Order Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Item updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input was supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order Item not found",
                    content = @Content)})
    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemReadOnlyDTO> updateOrderItem(
            @PathVariable Long orderItemId,
            @RequestBody OrderItemUpdateDTO orderItemUpdateDTO) {
        OrderItemReadOnlyDTO updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemUpdateDTO);
        if (updatedOrderItem != null) {
            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete an Order Item by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Item Deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderItemReadOnlyDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Order Item not found",
                    content = @Content)})
    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<OrderItemReadOnlyDTO> deleteOrderItem(@PathVariable Long orderItemId) {
        OrderItemReadOnlyDTO deletedOrderItem = orderItemService.deleteOrderItem(orderItemId);
        if (deletedOrderItem != null) {
            return new ResponseEntity<>(deletedOrderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



