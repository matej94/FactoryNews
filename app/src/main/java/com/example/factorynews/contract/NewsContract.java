package com.example.factorynews.contract;

import com.example.factorynews.model.News;

import java.util.List;

import io.realm.RealmResults;

public interface NewsContract {
    interface View{
        void showNews(RealmResults<News> newsList);

    }
    interface Presenter{
        void getNews();
    }
}
