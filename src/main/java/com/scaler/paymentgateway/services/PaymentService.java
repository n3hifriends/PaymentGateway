package com.scaler.paymentgateway.services;

import com.razorpay.RazorpayException;
import com.scaler.paymentgateway.paymentgateways.IPaymentGateway;
import com.scaler.paymentgateway.paymentgateways.PaymentGatewayChooserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    @Override
    public String getPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) throws RazorpayException {
        IPaymentGateway iPaymentGateway = paymentGatewayChooserStrategy.getPaymentGateway("RAZORPAY");
        return iPaymentGateway.createStandardPaymentLink(amount, orderId, phoneNumber, name, email);
    }
}
