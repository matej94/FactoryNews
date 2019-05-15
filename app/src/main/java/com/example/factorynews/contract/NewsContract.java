package com.example.factorynews.contract;

import com.example.factorynews.model.News;

import java.util.List;

public interface NewsContract {
    interface View{
        void showNews(List<News> newsList);

    }
    interface Presenter{
        void getNews();
    }
}
