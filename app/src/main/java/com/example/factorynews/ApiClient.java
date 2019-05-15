package com.example.factorynews;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://newsapi.org/v1/";
    private static Retrofit retrofit;
    private static IApiClient apiClient;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient())
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
