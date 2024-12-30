package br.com.spring.urlshorter.services;

import br.com.spring.urlshorter.dto.userurl.CreateUrlRequest;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.util.HashMap;
import java.util.Map;


@Service
@Log4j2
@RequiredArgsConstructor
public class LambdaProducer {

    @Value("${create-shortner-url-function}")
    private String functionName;

    private final LambdaClient lambdaClient;

    public String createShortUrl(String payload) {

        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);

        log.info("Response: " + invokeResponse.payload().asUtf8String());

        return invokeResponse.payload().asUtf8String();
    }
}
