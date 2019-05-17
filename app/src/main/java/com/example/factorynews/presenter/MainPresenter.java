package com.example.factorynews.presenter;

import com.example.factorynews.database.IDatabaseManager;
import com.example.factorynews.network.INetworkManager;
import com.example.factorynews.contract.NewsContract;
import com.example.factorynews.model.News;
import com.example.factorynews.model.NewsResponse;

import java.util.ArrayList;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements NewsContract.Presenter {
    private NewsContract.View view;
    private INetworkManager networkManager;
    private IDatabaseManager databaseManager;
    private ArrayList<News> newsList;
    private RealmResults<News> realmList;

    public MainPresenter(NewsContract.View view, INetworkManager networkManager, IDatabaseManager databaseManager) {
        this.view = view;
        this.networkManager = networkManager;
        this.databaseManager = databaseManager;

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
                    databaseManager.deleteNews(realmList);
                    NewsResponse newsResp = response.body();
                    newsList = newsResp.getNewsList();
                    for(int i = 0; i < newsList.size();i++) {
                        String author = newsList.get(i).getAuthor();
                        String title = newsList.get(i).getTitle();
                        String description = newsList.get(i).getDescription();
                        String url = newsList.get(i).getUrl();
                        String urlToImage = newsList.get(i).getUrlToImage();
                        String published = newsList.get(i).getPublishedAt();
                        databaseManager.addNews(author,title,description,url,urlToImage,published);
                    }
                    realmList = databaseManager.getAllNews();
                    view.showNews(realmList);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                realmList = databaseManager.getAllNews();
                view.showNews(realmList);
            }
        };
    }
}
