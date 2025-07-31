package com.amhue.hman.Controllers;

import java.util.Map;

import com.amhue.hman.StripeDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    @Value("${stripe.api.key}") private String stripeApiKey;

    @PostMapping
    public ResponseEntity<?> pay(@RequestBody StripeDTO stripeDTO) {
        Stripe.apiKey = stripeApiKey;
        System.out.println("Start");
        try {
            PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                    .setAmount(stripeDTO.getAmount())
                    .setCurrency(stripeDTO.getCurrency())
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            System.out.println("Processed stripe");
            return ResponseEntity.ok(
                Map.of("client_secret", intent.getClientSecret()));
        } catch (StripeException e) {
            e.printStackTrace();
            System.out.println("Error stripe");
            return ResponseEntity.status(500).body(
                Map.of("error", e.getMessage()));
        }
    }
}
