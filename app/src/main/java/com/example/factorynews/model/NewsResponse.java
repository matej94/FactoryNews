package com.example.factorynews.model;

import com.example.factorynews.model.News;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("articles")
    private List<News> newsList;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
