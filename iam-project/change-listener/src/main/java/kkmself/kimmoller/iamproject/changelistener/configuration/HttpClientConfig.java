package kkmself.kimmoller.iamproject.changelistener.configuration;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

  @Bean
  public OkHttpClient OkHttpClient() {
    return new OkHttpClient();
  }
}
