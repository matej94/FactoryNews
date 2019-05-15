package com.example.factorynews;

import com.example.factorynews.model.NewsResponse;

import retrofit2.Callback;

public interface INetworkManager{
        void getNewsList(Callback<NewsResponse> newsListCallback);
}
