package kkmself.kimmoller.iamproject.changelistener;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;

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

  public <T> T toObject(Response response, Class<T> type) throws IOException {
    return objectMapper.readValue(response.body().string(), type);
  }

  public <T> List<T> toList(Response response, Class<T> classz) throws IOException {
    JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, classz);
    List<T> returnValue = objectMapper.readValue(response.body().string(), type);
    response.close();
    return returnValue;
  }

  private class TestClass {
    private Response response;

    // Upper toList here

    // Return this object from getRequest so that that call can then call this toLisT
    public toList() {

    }

    public toObject() {

    }
  }
}
