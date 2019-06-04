package com.example.factorynews.model;

import com.example.factorynews.model.News;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

// JSON response contains a JSON array name ‘articles’ which contains multiple JSON objects each of which hold news details.
// To recieve this JSON we will need two model classes:
// NewsResponse - for the JSON Array
// News - for each JSON object.
public class NewsResponse {
    @SerializedName("articles")
    private ArrayList<News> newsList;

    //getter and setter
    public ArrayList<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(ArrayList<News> newsList) {
        this.newsList = newsList;
    }
}
