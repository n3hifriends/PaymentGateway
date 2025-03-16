package com.scaler.paymentgateway.paymentgateways;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// https://docs.stripe.com/api/payment-link/create

// Test card no.: https://docs.stripe.com/testing
@Component
public class StripePaymentGateway implements IPaymentGateway {

    @Value("${stripe.api}")
    private String stripeKey;

    @Override
    public String createStandardPaymentLink(Long amount, String orderId, String phoneNumber, String name, String email) {
        try {
            Stripe.apiKey = stripeKey;
            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(getPrice(amount).getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .setAfterCompletion(
                                    PaymentLinkCreateParams.AfterCompletion.builder().setType(
                                                    PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                            .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                    .setUrl("https://scaler.com").build()).build())
                            .build();
            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Price getPrice(Long amount) {
        try {
            Stripe.apiKey = this.stripeKey;

            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("usd")
                            .setUnitAmount(amount)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();

            return Price.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
