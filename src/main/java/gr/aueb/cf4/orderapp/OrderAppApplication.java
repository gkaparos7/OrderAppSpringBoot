package gr.aueb.cf4.orderapp;

import gr.aueb.cf4.orderapp.dto.*;
import gr.aueb.cf4.orderapp.model.*;
import gr.aueb.cf4.orderapp.repository.OrderRepository;
import gr.aueb.cf4.orderapp.repository.ProductRepository;
import gr.aueb.cf4.orderapp.repository.WishlistRepository;
import gr.aueb.cf4.orderapp.rest.UserController;
import gr.aueb.cf4.orderapp.service.IProductService;
import gr.aueb.cf4.orderapp.service.IUserService;

import gr.aueb.cf4.orderapp.service.mappers.WishlistItemMapper;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static gr.aueb.cf4.orderapp.model.Size.*;


@SpringBootApplication
public class OrderAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(OrderAppApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner init(UserController userController, IUserService userService, IProductService productService, WishlistItemMapper wishlistItemMapper, WishlistRepository wishlistRepository, OrderRepository orderRepository) {
		return args -> {
			// Create the admin user
			UserCreateDTO adminDTO = new UserCreateDTO("admin@admin.gr", "12345");
			userController.registerUser(adminDTO);

			// Create another user
			UserCreateDTO userDTO = new UserCreateDTO("user@hotmail.com", "12345");
			userController.registerUser(userDTO);

			// Create another user
			UserCreateDTO admin1DTO = new UserCreateDTO("user@gmail.gr", "12345");
			userController.registerUser(admin1DTO);

			User user = userService.getUserEntityById(1L);
			Wishlist wishlist = user.getWishlist();

			// Manually initialize the collection
			Hibernate.initialize(wishlist.getWishlistItems());

			// Retrieve the product with the specified details
			Product product = productService.getProductEntityById(1L);

			// Create a WishlistItemCreateDTO
			WishlistItemCreateDTO wishlistItemCreateDTO = new WishlistItemCreateDTO(12, SIZE_110, product, wishlist);

			// Convert the DTO to an entity
			WishlistItem wishlistItem = wishlistItemMapper.convertToWishlistItemEntity(wishlistItemCreateDTO);

			// Associate the WishlistItem with the Wishlist
			wishlist.addWishlistItem(wishlistItem);

			// Save the updated Wishlist
			wishlistRepository.save(wishlist);

			// Retrieve the product with the specified details
			Product product2 = productService.getProductEntityById(1L);

			// Create a WishlistItemCreateDTO
			WishlistItemCreateDTO wishlistItemCreateDTO2 = new WishlistItemCreateDTO(15, SIZE_140, product2, wishlist);

			// Convert the DTO to an entity
			WishlistItem wishlistItem2 = wishlistItemMapper.convertToWishlistItemEntity(wishlistItemCreateDTO2);

			// Associate the WishlistItem with the Wishlist
			wishlist.addWishlistItem(wishlistItem2);

			// Save the updated Wishlist
			wishlistRepository.save(wishlist);

			System.out.println("WishlistItems created successfully.");


			if (user != null) {
				// Create orders with order items
				createOrdersWithOrderItems(user, productService, orderRepository);
			} else {
				System.out.println("User with ID 2 not found.");
			}
		};
	}
	private void createOrdersWithOrderItems(User user, IProductService productService, OrderRepository orderRepository) {
		// Retrieve the product with the specified details
		Product product1 = productService.getProductEntityById(1L);
		Product product2 = productService.getProductEntityById(2L);
		Product product3 = productService.getProductEntityById(3L);

		OrderItem orderItem1 = new OrderItem(20, SIZE_110, product1, null);
		OrderItem orderItem2 = new OrderItem(15, SIZE_140, product2, null);
		OrderItem orderItem3 = new OrderItem(32, SIZE_160, product3, null);

		// Create an Order
		Order order = new Order();
		order.setUser(user);
		order.setOrderItems(List.of(orderItem1, orderItem2, orderItem3));

		// Set the order for each order item
		orderItem1.setOrder(order);
		orderItem2.setOrder(order);
		orderItem3.setOrder(order);

		// Set the order date
		order.setOrderDate(new Date());

		// Save the order
		Order savedOrder = orderRepository.save(order);

		// Create new instances of OrderItem for the second order
		OrderItem orderItem4 = new OrderItem(26, SIZE_110, product1, null);
		OrderItem orderItem5 = new OrderItem(18, SIZE_140, product2, null);
		OrderItem orderItem6 = new OrderItem(39, SIZE_160, product3, null);

		// Create a new Order
		Order order1 = new Order();
		order1.setUser(user);
		order1.setOrderItems(List.of(orderItem4, orderItem5, orderItem6));

		// Set the order for each order item
		orderItem4.setOrder(order1);
		orderItem5.setOrder(order1);
		orderItem6.setOrder(order1);

		// Set the order date
		order1.setOrderDate(new Date());

		// Save the second order
		Order savedOrder1 = orderRepository.save(order1);

		System.out.println("Orders with OrderItems created successfully. Order IDs: " + savedOrder.getId() + ", " + savedOrder1.getId());
	}

}
