package com.example.factorynews.model;

import com.example.factorynews.model.News;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsResponse {
    @SerializedName("articles")
    private ArrayList<News> newsList;

    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }
}
