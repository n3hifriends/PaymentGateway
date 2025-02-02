package com.scaler.paymentgateway.controller;

import com.razorpay.RazorpayException;
import com.scaler.paymentgateway.dtos.InitiatePaymentDto;
import com.scaler.paymentgateway.services.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto) throws RazorpayException {
        return paymentService.getPaymentLink(initiatePaymentDto.getAmount(), initiatePaymentDto.getOrderId(),
                initiatePaymentDto.getPhoneNumber(), initiatePaymentDto.getName(), initiatePaymentDto.getEmail());
    }
}
