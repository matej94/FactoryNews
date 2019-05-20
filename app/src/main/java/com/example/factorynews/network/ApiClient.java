package com.example.factorynews.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://newsapi.org/v1/";
    private static Retrofit retrofit;
    private static IApiClient apiClient;
     private static OkHttpClient getClient(){
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES);
            return builder.build();
        }
    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static IApiClient getApiServiceInstance() {
        if (apiClient == null) {
            apiClient = getRetrofitInstance().create(IApiClient.class);
        }
        return apiClient;
    }
}
