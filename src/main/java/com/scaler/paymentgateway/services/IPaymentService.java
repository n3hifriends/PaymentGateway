package com.scaler.paymentgateway.services;

import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

public interface IPaymentService {
    String getPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) throws RazorpayException ;
}
