package com.foodapp.helper;

import com.foodapp.DTO.CartItemDTO;
import com.foodapp.DTO.OrderDTO;
import com.foodapp.DTO.OrderPreviewDTO;
import com.foodapp.DTO.OrderedItemDTO;
import com.foodapp.entity.CartItem;
import com.foodapp.entity.Order;
import com.foodapp.entity.OrderedItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


import static com.foodapp.helper.CartItemMapper.toDTO;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setEmail(order.getEmail());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setHandlingFee(order.getHandlingFee());
        orderDTO.setPlatformFee(order.getPlatformFee());
        orderDTO.setGSTFee(order.getGSTFee());
        orderDTO.setTotalItemCost(order.getTotalItemCost());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setDeliveryAddress(order.getDeliveryAddress());
        orderDTO.setPaymentMethod(order.getPaymentMethod());

        // 🟡 These were incorrect or missing
        if (order.getOrderDate() != null)
            orderDTO.setOrderDate(LocalDate.parse(order.getOrderDate())); // String to LocalDate
        if (order.getOrderTime() != null)
            orderDTO.setOrderTime(LocalTime.parse(order.getOrderTime())); // String to LocalTime

        orderDTO.setIsAccepted(order.getAccepted());
        orderDTO.setName(order.getCustomer() != null ? order.getCustomer().getName() : null);
        orderDTO.setPhoneNumber(order.getDeliveryRecieverPhoneNumber());

        // ✅ Map OrderedItems -> CartItemDTO
        if (order.getOrderItems() != null) {
            List<OrderedItemDTO> items = order.getOrderItems()
                    .stream()
                    .map(OrderMapper::toOrderedItemDTO)
                    .collect(Collectors.toList());
            orderDTO.setOrderItems(items);
        }

        return orderDTO;
    }

    public static OrderedItemDTO toOrderedItemDTO(OrderedItem item) {
        OrderedItemDTO dto = new OrderedItemDTO();
        dto.setItemName(item.getItemName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        dto.setImage(item.getImage());
        return dto;
    }



    public static OrderPreviewDTO toDTO(
                String name,
                String deliveryAddress,
                String paymentMode,
                String  deliveryRecieverPhoneNumber,
                double totalItemCost,
                double handlingFee,
                double platformFee,
                double gstFee,
                double totalAmount,
                List<CartItemDTO> cartItems) {

            return new OrderPreviewDTO(
                    name,
                    deliveryAddress,
                    paymentMode,
                    deliveryRecieverPhoneNumber,
                    totalItemCost,
                    handlingFee,
                    platformFee,
                    gstFee,
                    totalAmount,
                    cartItems
            );
        }


}
