package kr.seoulmaas.ieye.service.utill;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {

    private static int TIME_OUT = 5000;
    private static int MAX_CONNECTION_POOL = 100;
    private static int MAX_CONNECTION_PER = 5;

    private RestTemplate restTemplate;

    //TODO 메소드 네이밍 변경
    public void get() {
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
}