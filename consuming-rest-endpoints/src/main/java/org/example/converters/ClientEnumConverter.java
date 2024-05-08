package org.example.converters;

import org.example.controllers.PaymentController;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class ClientEnumConverter implements Converter<String, PaymentController.Client> {
    @Override
    public PaymentController.Client convert(@NonNull String source) {
        return PaymentController.Client.getFromClientName(source);
    }
}
