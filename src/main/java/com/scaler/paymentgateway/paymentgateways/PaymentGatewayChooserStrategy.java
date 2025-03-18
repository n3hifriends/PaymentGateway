package com.scaler.paymentgateway.paymentgateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayChooserStrategy {

    @Autowired
    private RazorPaymentGateway razorPaymentGateway;

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public IPaymentGateway getPaymentGateway(String paymentGatewayName){
        return stripePaymentGateway;
    }
}
