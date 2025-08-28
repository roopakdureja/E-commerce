package com.ecommerce.project.service;

import com.ecommerce.project.payload.OrderDTO;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface OrderService {

    OrderDTO placeOrder(String emailId, Integer addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}
