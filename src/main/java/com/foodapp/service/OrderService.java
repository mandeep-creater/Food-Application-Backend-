package com.foodapp.service;

import com.foodapp.DTO.OrderDTO;
import com.foodapp.DTO.OrderPreviewDTO;
import com.foodapp.DTO.OrderRequestDTO;

import java.util.List;

public interface OrderService {

    // 0️⃣ Preview order before placing
  OrderPreviewDTO previewOrder(String userEmail, OrderRequestDTO orderRequestDTO);

    //OrderPreviewDTO previewOrder(String userEmail, String deliveryAddress);

    // 1. Place new order (based on cart items)
    OrderDTO placeOrder(String userEmail , OrderRequestDTO orderRequestDTO);

    // 2. Get all orders of a user
    List<OrderDTO> getUserOrders(String userEmail);

    // 3. Get single order by ID
    OrderDTO getOrderById(Long orderId);

    // 4. Cancel order
    void cancelOrder(Long orderId, String userEmail);

    // 5. Accept order (by restaurant)
    void acceptOrder(Long orderId);

    // 6. Change status: preparing, delivered, etc.
    void updateOrderStatus(Long orderId, String status);

    // 7. Get all orders (admin or restaurant view)
    List<OrderDTO> getAllOrders();

    // 8. Get all orders with a specific status (e.g. "PREPARING", "DELIVERED")
    List<OrderDTO> getOrdersByStatus(String status);

    // 9. Update payment status after external gateway confirmation (e.g. "PAID", "FAILED")
    void updatePaymentStatus(Long orderId, String paymentStatus);

    // 10. Get all ongoing/live orders for a user (not delivered or cancelled)
    List<OrderDTO> getLiveOrdersForUser(String userEmail);

    // 11. Get today’s orders for a specific restaurant (used for dashboard/statistics)
    List<OrderDTO> getTodayOrders(Long restaurantId);

    // 12. Recalculate preview when cart or item price changes before final order
    OrderPreviewDTO recalculatePreview(String userEmail);

    // 13. Check if a particular order is eligible for cancellation (based on status/timing)
    boolean isOrderCancellable(Long orderId, String userEmail);

    // 14. Get status timeline of an order (e.g. PLACED → ACCEPTED → PREPARING → DELIVERED)
    List<String> getOrderTimeline(Long orderId);

}
