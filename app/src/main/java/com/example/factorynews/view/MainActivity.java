package com.example.factorynews.view;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.factorynews.database.DatabaseManager;
import com.example.factorynews.R;
import com.example.factorynews.contract.NewsContract;
import com.example.factorynews.model.News;
import com.example.factorynews.view.adapter.NewsAdapter;
import com.example.factorynews.network.NetworkManager;
import com.example.factorynews.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity implements NewsContract.View{
    private NewsContract.Presenter presenter;
    @BindView(R.id.news_recycler) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

        // Instantiate the Presenter and ask to load data
        presenter = new MainPresenter(this, NetworkManager.getInstance(), DatabaseManager.getDatabaseInstance(),this);
        presenter.getNews();

    }
    private void initView(){
        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showNews(RealmResults<News> realmList) {
        // Pass results to NewsAdapter Class and binds adapter to the RecyclerView
        NewsAdapter adapter = new NewsAdapter(this,realmList);
        recyclerView.setAdapter(adapter);
    }
}
