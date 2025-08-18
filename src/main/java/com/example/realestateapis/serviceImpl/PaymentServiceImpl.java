package com.example.realestateapis.serviceImpl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.realestateapis.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final WebClient webClient = WebClient.builder().build();
    @Autowired
    private PaymentService paymentService;
    @Value("${paystack.secret}")
    private String secret;
    @Value("${paystack.base-url}")
    private String baseUrl;
    @Value("${paystack.callback-url}")
    private String callbackUrl;

    @Override
    public Mono<Map<String, Object>> verifyPayment(String reference) {
        return webClient.get()
                .uri(baseUrl + "/transaction/verify" + reference)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + secret)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}
