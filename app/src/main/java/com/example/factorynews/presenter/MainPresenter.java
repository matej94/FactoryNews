package com.example.factorynews.presenter;

import com.example.factorynews.network.INetworkManager;
import com.example.factorynews.contract.NewsContract;
import com.example.factorynews.model.News;
import com.example.factorynews.model.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements NewsContract.Presenter {
    private NewsContract.View view;
    private INetworkManager networkManager;
    private List<News> newsList;

    public MainPresenter(NewsContract.View view, INetworkManager networkManager) {
        this.view = view;
        this.networkManager = networkManager;

    }
    @Override
    public void getNews() {
        networkManager.getNewsList(getNewsListCallback());

    }
    protected Callback<NewsResponse> getNewsListCallback() {
        return new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response != null && response.body() != null ) {
                    newsList = response.body().getNewsList();
                    view.showNews(newsList);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {

            }
        };
    }
}
