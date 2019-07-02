package kr.seoulmaas.ieye.service.utill;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Component
@Slf4j
public class RestTemplateConfig {

    private static int TIME_OUT = 50000;
    private static int MAX_CONNECTION_POOL = 100;
    private static int MAX_CONNECTION_PER = 5;
    private static final String UTF_8 = "UTF-8";

    private RestTemplate restTemplate;

    public RestTemplateConfig() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(TIME_OUT);
        factory.setConnectTimeout(TIME_OUT);
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONNECTION_POOL)
                .setMaxConnPerRoute(MAX_CONNECTION_PER)
                .build();
        factory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(factory);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
