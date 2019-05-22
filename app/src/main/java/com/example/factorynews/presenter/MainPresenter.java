package com.example.factorynews.presenter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.example.factorynews.database.IDatabaseManager;
import com.example.factorynews.network.INetworkManager;
import com.example.factorynews.contract.NewsContract;
import com.example.factorynews.model.News;
import com.example.factorynews.model.NewsResponse;
import com.google.gson.GsonBuilder;

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
    private Context context;

    public MainPresenter(NewsContract.View view, INetworkManager networkManager, IDatabaseManager databaseManager, Context context) {
        this.view = view;
        this.networkManager = networkManager;
        this.databaseManager = databaseManager;
        this.context = context;
    }

    @Override
    public void getNews() {
        networkManager.getNewsList(getNewsListCallback());
    }

    protected Callback<NewsResponse> getNewsListCallback() {
        final ProgressDialog mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading news...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        return new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response != null && response.body() != null) {
                    Log.w("2.0 getFeed", new GsonBuilder().setPrettyPrinting().create().toJson(response));

                    mProgressDialog.dismiss();
                    databaseManager.deleteNews(realmList);
                    NewsResponse newsResp = response.body();
                    newsList = newsResp.getNewsList();
                    for (int i = 0; i < newsList.size(); i++) {
                        String author = newsList.get(i).getAuthor();
                        String title = newsList.get(i).getTitle();
                        String description = newsList.get(i).getDescription();
                        String url = newsList.get(i).getUrl();
                        String urlToImage = newsList.get(i).getUrlToImage();
                        String published = newsList.get(i).getPublishedAt();
                        databaseManager.addNews(author, title, description, url, urlToImage, published);
                    }
                    realmList = databaseManager.getAllNews();
                    view.showNews(realmList);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                realmList = databaseManager.getAllNews();
                view.showNews(realmList);
                mProgressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Something went wrong!");
                builder.setTitle("Error !");
                builder.setCancelable(false);
                builder.setNegativeButton(
                                "OK",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        };
    }
}