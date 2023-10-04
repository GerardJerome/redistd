import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SearchUtil {

    public static String makeSearch(String word) throws IOException {
        RedisUtil.store(word);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://apidico.oa.r.appspot.com/repo/" + word)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if(response.body()!=null){
            return response.body().string();
        }else{
            return "ERROR";
        }
    }
}
