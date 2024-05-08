package org.example.clients;

import org.example.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class PaymentWebClient {
    private final WebClient webClient;
    private final String url;

    @Autowired public PaymentWebClient(WebClient webClient, @Value("${payment.service.url}") String url) {
        this.webClient = webClient;
        this.url = url;
    }

    public Mono<Payment> createPayment(String requestId, Payment payment) {
        return webClient
                .post()
                .uri(url + "/payment")
                .header("requestId", requestId)
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }
}
