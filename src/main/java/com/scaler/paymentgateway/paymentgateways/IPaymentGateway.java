package com.scaler.paymentgateway.paymentgateways;

// https://razorpay.com/docs/api/payments/payment-links/create-standard?search-string=create%20a%20standard

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;


public interface IPaymentGateway {
    String createStandardPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) ;
}
