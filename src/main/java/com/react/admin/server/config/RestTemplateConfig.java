package com.react.admin.server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        jsonConverter.setObjectMapper(objectMapper);

        MappingJackson2HttpMessageConverter mediaTypeConverter = new MappingJackson2HttpMessageConverter();
        mediaTypeConverter.setSupportedMediaTypes(Arrays.asList(
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_HTML,
                MediaType.APPLICATION_JSON));

        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(jsonConverter);
        converters.add(mediaTypeConverter);
        RestTemplate restTemplate = new RestTemplate(
                new HttpComponentsClientHttpRequestFactory(restTemplateConfigHttpClient()));
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    public HttpClient restTemplateConfigHttpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        // 设置整个连接池最大连接数 根据自己的场景决定
        connectionManager.setMaxTotal(1000);
        // 路由是对maxTotal的细分
        connectionManager.setDefaultMaxPerRoute(1000);
        RequestConfig requestConfig = RequestConfig.custom()
                // 从连接池中获取连接的超时时间，超过该时间未拿到可用连接，
                // 会抛出org.apache.http.conn.ConnectionPoolTimeoutException:
                // Timeout waiting for connection from pool
                .setConnectionRequestTimeout(Timeout.ofSeconds(60))
                .build();
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();
    }

}
