package com.example.factorynews.presenter;

import com.example.factorynews.contract.NewsContract;

public class MainPresenter implements NewsContract.Presenter {
    private NewsContract.View view;

    public MainPresenter(NewsContract.View view) {
        this.view = view;
    }
    @Override
    public void getNews() {

    }
}
