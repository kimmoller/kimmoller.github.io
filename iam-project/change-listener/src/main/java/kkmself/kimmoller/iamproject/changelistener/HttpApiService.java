package kkmself.kimmoller.iamproject.changelistener;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HttpApiService {
  private final OkHttpClient okHttpClient;
  private final ObjectMapper objectMapper;

  public Response getRequest(String url) throws IOException {
    var request = new Request.Builder().url(url).build();
    return okHttpClient.newCall(request).execute();
  }

  public Response patchRequest(String url, Object requestBody) throws IOException {
    var request =
        new Request.Builder()
            .url(url)
            .patch(
                RequestBody.create(
                    objectMapper.writeValueAsString(requestBody), MediaType.parse("JSON")))
            .header("Content-Type", "application/json")
            .build();
    return okHttpClient.newCall(request).execute();
  }
}
