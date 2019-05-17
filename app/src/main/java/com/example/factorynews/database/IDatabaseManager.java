package com.example.factorynews.database;

import com.example.factorynews.model.News;

import io.realm.RealmResults;

public interface IDatabaseManager {
    RealmResults<News> getAllNews();
    void addNews(String author, String title, String description, String url, String urlToImage, String publishedAt );
    void deleteNews(RealmResults<News> rlmArray);
}
