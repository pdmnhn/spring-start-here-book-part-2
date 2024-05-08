package org.example.controllers;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.example.clients.PaymentOpenFeignClient;
import org.example.clients.PaymentRestTemplateClient;
import org.example.clients.PaymentWebClient;
import org.example.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PaymentController {
    public enum Client {
        OPEN_FEIGN("open-feign"),
        REST_TEMPLATE("rest-template"),
        WEB_CLIENT("web-client");
        private final String clientName;

        private Client(String clientName) {
            this.clientName = clientName;
        }

        @Override
        public String toString() {
            return this.clientName;
        }

        private static final Map<String, Client> CLIENT_MAP =
                Arrays.stream(values()).collect(Collectors.toMap(Client::toString, client -> client));
        public static Client getFromClientName(String clientName) {
            return CLIENT_MAP.get(clientName);
        }
    }

    private final PaymentOpenFeignClient paymentOpenFeignClient;
    private final PaymentRestTemplateClient paymentRestTemplateClient;
    private final PaymentWebClient paymentWebClient;

    @Autowired
    public PaymentController(PaymentOpenFeignClient paymentOpenFeignClient,
            PaymentRestTemplateClient paymentRestTemplateClient, PaymentWebClient paymentWebClient) {
        this.paymentOpenFeignClient = paymentOpenFeignClient;
        this.paymentRestTemplateClient = paymentRestTemplateClient;
        this.paymentWebClient = paymentWebClient;
    }

    @PostMapping("/payment")
    public Payment createPayment(@RequestBody Payment payment, @RequestParam Client client) {
        String requestId = UUID.randomUUID().toString();
        return switch (client) {
            case OPEN_FEIGN -> paymentOpenFeignClient.createPayment(requestId, payment);
            case REST_TEMPLATE -> paymentRestTemplateClient.createPayment(requestId, payment);
            case WEB_CLIENT -> paymentWebClient.createPayment(requestId, payment).block();
        };
    }
}
