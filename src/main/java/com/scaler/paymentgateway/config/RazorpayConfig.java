package com.scaler.paymentgateway.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {

    @Value("${razorpay_id}")
    private String razorpayApiKey;

    @Value("${razorpay_secret}") // reads for properties, secret manager
    private String razorpayApiSecret;

    @Bean
    public RazorpayClient razorpayClient() {
        try {
            return new RazorpayClient(razorpayApiKey, razorpayApiSecret);
        } catch (RazorpayException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
