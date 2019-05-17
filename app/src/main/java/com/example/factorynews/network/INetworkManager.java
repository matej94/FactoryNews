package com.example.factorynews.network;

import com.example.factorynews.model.NewsResponse;

import retrofit2.Callback;

public interface INetworkManager{
        void getNewsList(Callback<NewsResponse> newsListCallback);
}
