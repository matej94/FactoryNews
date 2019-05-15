package com.example.factorynews;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.factorynews.contract.NewsContract;
import com.example.factorynews.model.News;
import com.example.factorynews.presenter.MainPresenter;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NewsContract.View{
    private NewsContract.Presenter presenter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        presenter = new MainPresenter(this);
        presenter.getNews();

    }
    private void initView(){
        recyclerView = findViewById(R.id.news_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showNews(List<News> newsList) {

    }
}
