package localizae.net.br.helper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebRequest {

    public static final String TAG = "WEB_REQUEST";
    public static final String BASE_URL = "http://18.220.190.201:8080/localizae/";

    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
