package com.example.factorynews.network;

import com.example.factorynews.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IApiClient {
    @GET("articles")
    Call<NewsResponse> getNewsData(@Query("source") String source, @Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}
