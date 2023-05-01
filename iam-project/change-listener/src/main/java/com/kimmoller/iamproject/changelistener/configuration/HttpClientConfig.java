package com.kimmoller.iamproject.changelistener.configuration;

import java.net.http.HttpClient;
import java.time.Duration;
import org.springframework.context.annotation.Bean;

public class HttpClientConfig {

  @Bean
  public HttpClient httpClient() {
    return HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(60)).build();
  }
}
