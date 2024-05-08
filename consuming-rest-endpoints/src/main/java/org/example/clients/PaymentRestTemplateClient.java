package org.example.clients;

import java.util.UUID;
import org.example.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class PaymentRestTemplateClient {
    private final RestTemplate restTemplate;
    private final String paymentServiceUrl;

    @Autowired
    public PaymentRestTemplateClient(RestTemplate restTemplate,
            @Value("${payment.service.url}") String paymentServiceUrl) {
        this.restTemplate = restTemplate;
        this.paymentServiceUrl = paymentServiceUrl;
    }

    public Payment createPayment(String requestId, Payment payment) {
        String uri = paymentServiceUrl + "/payment";
        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", requestId);
        HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);

        ResponseEntity<Payment> response = restTemplate.exchange(uri, HttpMethod.POST, entity, Payment.class);

        return response.getBody();
    }
}
