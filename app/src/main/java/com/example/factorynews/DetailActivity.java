package com.example.factorynews;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.factorynews.contract.NewsContract;
import com.example.factorynews.database.DatabaseManager;
import com.example.factorynews.model.News;
import com.example.factorynews.network.NetworkManager;
import com.example.factorynews.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class DetailActivity extends AppCompatActivity implements NewsContract.View {
   private NewsContract.Presenter presenter;

    @BindView(R.id.view_pager) ViewPager viewPager;
   private int position;
   DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, NetworkManager.getInstance(), DatabaseManager.getDatabaseInstance(),this);
        presenter.getNews();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void showNews(RealmResults<News> newsList) {
        position = getIntent().getIntExtra("item_position",0);
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);
        detailAdapter = new DetailAdapter(this,newsList);
        viewPager.setAdapter(detailAdapter);
        viewPager.setCurrentItem(position);

    }
}
