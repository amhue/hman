// package com.amhue.hman.Controllers;

// import java.util.Map;

// import com.stripe.Stripe;
// import com.stripe.exception.StripeException;
// import com.stripe.param.checkout.SessionCreateParams;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import jakarta.annotation.PostConstruct;

// @RestController
// @RequestMapping("/api/checkout")
// public class CheckoutController {
//     @Value("${stripe.api.key}") private String stripeSecretKey;

//     @PostConstruct
//     public void init() {
//         Stripe.apiKey = stripeSecretKey;
//     }

//     @PostMapping
//     public ResponseEntity<Map<String, Object>>
//     checkout(@RequestBody Map<String, Object> data) {
//         try {
//             Long amount = Long.parseLong(data.get("amount").toString());

//             SessionCreateParams params =
//                 SessionCreateParams.builder()
//                     .setMode(SessionCreateParams.Mode.PAYMENT)
//                     .setSuccessUrl("http://localhost:5173/success")
//                     .setCancelUrl("http://localhost:5173/cancel")
//                     .addLineItem(SessionCreateParams.LineItem.builder())

//                         return ResponseEntity.ok();
//         } catch (StripeException e) {
//             return ResponseEntity.internalServerError().body(
//                 Map.of("error", e.getMessage()));
//         }
//     }
// }
