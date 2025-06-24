package com.foodapp.controller;


import com.foodapp.DTO.OrderDTO;
import com.foodapp.DTO.OrderPreviewDTO;
import com.foodapp.DTO.OrderRequestDTO;
import com.foodapp.Response.ApiResponse;
import com.foodapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @RequestMapping("/create")
//    public String createOrder() {
//        return "Order created successfully!";
//    }

//    @PostMapping("/preview")
//    public ResponseEntity<ApiResponse<OrderPreviewDTO>>previewOrder(Principal principal, @RequestBody OrderRequestDTO orderRequestDTO) {
//         String email = principal.getName();
//
//      OrderPreviewDTO res =orderService.previewOrder(email, orderRequestDTO);
//      ApiResponse<OrderPreviewDTO> response = new ApiResponse<>(HttpStatus.OK.value(), true,res);
//      return new ResponseEntity<>(response, HttpStatus.OK);
//    }


    @PostMapping("/preview")
    public ResponseEntity<ApiResponse<OrderPreviewDTO>>previewOrder(Principal principal, @RequestBody OrderRequestDTO  orderRequestDTO) {
        String email = principal.getName();

        OrderPreviewDTO res =orderService.previewOrder(email, orderRequestDTO);
        ApiResponse<OrderPreviewDTO> response = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/place")
      public ResponseEntity<ApiResponse<OrderDTO>>placeOrder(Principal principal, @RequestBody OrderRequestDTO  orderRequestDTO) {
        String email = principal.getName();
        OrderDTO res =  orderService.placeOrder(email,orderRequestDTO);
        ApiResponse<OrderDTO> response = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/user-orders")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getUserOrders(Principal principal) {
        String email = principal.getName();
        List<OrderDTO> res = orderService.getUserOrders(email);
        ApiResponse<List<OrderDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/cancel-order/{orderId}")
    public ResponseEntity<ApiResponse<String>> getOrderById(Principal principal, @PathVariable Long orderId) {
        String userEmail = principal.getName();
          orderService.cancelOrder( orderId, userEmail);
        String msg = "Order Cancelled successfully!";
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true,msg);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update-order-status/{orderId}")
    public ResponseEntity<ApiResponse<String>> updateOrderStatus(Principal principal, @PathVariable Long orderId, @RequestParam String status) {
        String userEmail = principal.getName();
        orderService.updateOrderStatus(orderId, status);
        String msg = "Order status updated successfully!";
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), true, msg);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getALLOrder")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders(Principal principal) {
        String email = principal.getName();
        List<OrderDTO> res = orderService.getAllOrders();
        ApiResponse<List<OrderDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), true,res);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
