package com.foodapp.serviceImpl;

import com.foodapp.DTO.CartItemDTO;
import com.foodapp.DTO.OrderDTO;
import com.foodapp.DTO.OrderPreviewDTO;
import com.foodapp.DTO.OrderRequestDTO;
import com.foodapp.entity.*;
import com.foodapp.exception.GlobalExceptionHandler;
import com.foodapp.helper.OrderMapper;
import com.foodapp.repo.CartItemRepo;
import com.foodapp.repo.MenuItemRepo;
import com.foodapp.repo.OrderRepo;
import com.foodapp.repo.UserRepository;
import com.foodapp.service.CartItemService;
import com.foodapp.service.OrderService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.foodapp.helper.OrderMapper.toDTO;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private MenuItemRepo menuItemRepo;



    @Override   //  Save/Update Address at Preview
    public OrderPreviewDTO previewOrder(String email, OrderRequestDTO orderRequestDTO) {
// 1. Authenticate user
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> GlobalExceptionHandler.userNotExists(User.class, email));
        // 2. Fetch cart items
        List<CartItemDTO> cartItems = cartItemService.getCartByUser(email);
        if(cartItems.isEmpty())
        {
             GlobalExceptionHandler.cartItemEmpty(email);
        }

        // 3. Calculate total price, handling fee, platform fee
        double totalItemCost = cartItemService.calculateCartTotal(email);
        double handlingFee = 0.05 * totalItemCost; // 5% of total item cost
        double platformFee = 0.02 * totalItemCost; // 2% of total item cost
        double gstFee=0.01*totalItemCost; // 1% of total item cost
        double totalAmount = totalItemCost + handlingFee + platformFee+gstFee;


//        Order order = orderRepo.findByEmailAndIsPreviewTrue(email)
//                .orElse(new Order());
//        System.out.println("Email :" + email +" __-->> "+user.getEmail());
        Order order = new Order();
        order.setCustomer(user);
        order.setDeliveryAddress(orderRequestDTO.getDeliveryAddress());
        order.setPaymentMethod(orderRequestDTO.getPaymentMode());
        order.setDeliveryRecieverPhoneNumber(orderRequestDTO.getDeliveryRecieverPhoneNumber());
        order.setPreview(true);
      //  orderRepo.save(order);
        // 4. Create OrderPreviewDTO
        return new OrderPreviewDTO(user.getName(), order.getDeliveryRecieverPhoneNumber(), orderRequestDTO.getDeliveryAddress(), order.getPaymentMethod(), totalItemCost, handlingFee, platformFee,gstFee, totalAmount,  cartItems);


    }


    @Override
    public OrderDTO placeOrder(String email , OrderRequestDTO orderRequestDTO) {

        // Step 1: Get user & preview order
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItemDTO> cartItems = cartItemService.getCartByUser(email);
        cartItems.forEach(cartItem -> {
            Long menuItemId = cartItem.getMenuItemId(); // assuming this exists
            int orderedQty = cartItem.getQuantity();
            MenuItem menuItem = menuItemRepo.findById(menuItemId)
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));

            if (menuItem.getQuantity() < orderedQty) {
                throw new RuntimeException("Insufficient stock for item: " + menuItem.getName());
            }

            menuItem.setQuantity(menuItem.getQuantity() - orderedQty);
            menuItemRepo.save(menuItem);
        });
        if (cartItems.isEmpty()) {
            GlobalExceptionHandler.cartItemEmpty(email);
        }
        // 3. Calculate total price, handling fee, platform fee
        double totalItemCost = cartItemService.calculateCartTotal(email);
        double handlingFee = 0.05 * totalItemCost; // 5% of total item cost
        double platformFee = 0.02 * totalItemCost; // 2% of total item cost
        double gstFee=0.01*totalItemCost; // 1% of total item cost
        double totalAmount = totalItemCost + handlingFee + platformFee+gstFee;

        // Step 2: Create final order
        Order finalOrder = new Order();
        finalOrder.setEmail(email);
        finalOrder.setPreview(false);
        finalOrder.setCustomer(user);
        finalOrder.setDeliveryAddress(orderRequestDTO.getDeliveryAddress());
        finalOrder.setPaymentMethod(orderRequestDTO.getPaymentMode());
        finalOrder.setDeliveryRecieverPhoneNumber(orderRequestDTO.getDeliveryRecieverPhoneNumber());
        finalOrder.setOrderStatus(Status.ACCEPTED);
        finalOrder.setOrderDate(LocalDate.now().toString());
        finalOrder.setOrderTime(LocalTime.now().toString());
        finalOrder.setAccepted(true);
        finalOrder.setTotalItemCost(totalItemCost);
        finalOrder.setHandlingFee(handlingFee);
        finalOrder.setPlatformFee(platformFee);
        finalOrder.setGSTFee(gstFee);
        finalOrder.setTotalAmount(totalAmount);

        Long menuItemId = cartItems.get(0).getMenuItemId();
        MenuItem menuItem = menuItemRepo.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        Restaurant restaurant = menuItem.getRestaurant();
        finalOrder.setRestaurant(restaurant);



//        // Step 3: Calculate total
//        double total = cartItemService.calculateCartTotal(email);
//        finalOrder.setTotalPrice(total);

        // Step 4: Map cart items to order items
        List<OrderedItem> orderItems = new ArrayList<>();
        for (CartItemDTO cart : cartItems) {
            OrderedItem item = new OrderedItem();
            item.setItemName(cart.getMenuItemName());
            item.setImage(cart.getMenuItemImage());
            item.setQuantity(cart.getQuantity());
            item.setPrice(cart.getMenuItemPrice());
            item.setOrder(finalOrder);
            orderItems.add(item);
        }

        finalOrder.setOrderItems(orderItems);

        // Step 5: Save and cleanup
        Order saved = orderRepo.save(finalOrder);
//        orderRepo.deleteByEmailAndIsPreviewTrue(email);
        cartItemService.clearCart(email);  // 🧹 Optional: clear cart

        return toDTO(saved);
    }



    @Override
    public List<OrderDTO> getUserOrders(String userEmail) {

        List<Order> orders = orderRepo.findAllByCustomerEmail(userEmail);
        if (orders.isEmpty()) {
          GlobalExceptionHandler.NoOrdersFoundForUser(userEmail);
        }
        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId, String userEmail) {
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"+userEmail));
        if (!order.getEmail().equals(userEmail)) {
            throw new RuntimeException("Invalid user for this order");
        }
        order.setOrderStatus(Status.CANCELLED_BY_CUSTOMER);
        orderRepo.save(order);

    }

    @Override
    public void acceptOrder(Long orderId) {
            Order order = orderRepo.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"+orderId));
        order.setOrderStatus(Status.ACCEPTED);
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
    Order order = orderRepo.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"+orderId));
    order.setOrderStatus(Status.valueOf(status));
    orderRepo.save(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
            List<Order> orders = orderRepo.findAll();
        if (orders.isEmpty()) {
            GlobalExceptionHandler.NoOrdersFound();
        }
        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        List<Order> orders = orderRepo.findAllByOrderStatus(Status.valueOf(status));
        if (orders.isEmpty()) {
            GlobalExceptionHandler.NoOrdersFound();
        }
        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public void updatePaymentStatus(Long orderId, String paymentStatus) {

    }

    @Override
    public List<OrderDTO> getLiveOrdersForUser(String userEmail) {
        List<Order> orders = orderRepo.findAllByCustomerEmailAndOrderStatus(userEmail, Status.ACCEPTED);
        if (orders.isEmpty()) {
            GlobalExceptionHandler.NoLiveOrdersForUser(userEmail);
        }
        return orders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public List<OrderDTO> getTodayOrders(Long restaurantId) {
        return null;
    }

    @Override
    public OrderPreviewDTO recalculatePreview(String userEmail) {
        return null;
    }

    @Override
    public boolean isOrderCancellable(Long orderId, String userEmail) {

        return false;
    }

    @Override
    public List<String> getOrderTimeline(Long orderId) {
        return null;
    }
}
