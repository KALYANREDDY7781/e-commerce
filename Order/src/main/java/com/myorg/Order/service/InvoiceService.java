package com.myorg.Order.service;

import com.myorg.Order.dto.InvoiceDTO;
import com.myorg.Order.dto.PaymentDTO;
import com.myorg.Order.dto.ProductDto;
import com.myorg.Order.entity.Order;
import com.myorg.Order.exception.OrderNotFoundException;
import com.myorg.Order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceService {

    private OrderRepository orderRepository;
    private PaymentClient paymentClient;
    private ProductClient productClient;

    public InvoiceDTO generateInvoice(int orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()){
            throw new OrderNotFoundException("Order not found for given orderId: "+orderId);
        }
        /*List<OrderItem> orderItems = order.get().getOrderItems();
        List<Integer> products = new ArrayList<>();
        orderItems.forEach(item -> {
            products.add(item.getProductId());
        });

        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(id -> {
            ProductDto productDto = productClient.getProductById(id);
            productDtos.add(productDto);
        });*/

        List<ProductDto> productDtos = order.get().getOrderItems().stream()
                .map(orderItem -> productClient.getProductById(orderItem.getProductId()))
                .collect(Collectors.toList());

        PaymentDTO paymentDTO = paymentClient.getPaymentByOrderId(orderId);
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setOrderId(orderId);
        invoiceDTO.setOrderDate(order.get().getOrderDate());
        invoiceDTO.setCustomerId(order.get().getCustomerId());
        invoiceDTO.setProducts(productDtos);
        invoiceDTO.setTotalAmount(order.get().getTotalAmount());
        invoiceDTO.setPaymentDTO(paymentDTO);


        return invoiceDTO;
    }
}
