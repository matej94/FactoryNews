package com.example.factorynews.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.factorynews.R;
import com.example.factorynews.contract.NewsContract;
import com.example.factorynews.database.DatabaseManager;
import com.example.factorynews.model.News;
import com.example.factorynews.network.NetworkManager;
import com.example.factorynews.presenter.MainPresenter;
import com.example.factorynews.view.adapter.DetailAdapter;

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

        // Instantiate the Presenter and ask to load data
        presenter = new MainPresenter(this, NetworkManager.getInstance(), DatabaseManager.getDatabaseInstance(),this);
        presenter.getNews();

        // Add back button to ActionBar
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void showNews(final RealmResults<News> newsList) {
        // Retrieve title and position of clicked item in recycler view
        position = getIntent().getIntExtra("item_position",0);
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);

        // Pass results to DetailAdapter Class and binds adapter to the ViewPager
        detailAdapter = new DetailAdapter(this,newsList);
        viewPager.setAdapter(detailAdapter);

        // Start ViewPager at position of item which is clicked in recycler view
        viewPager.setCurrentItem(position);

        // Change ActionBar title with every swipe in ViewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setTitle(newsList.get(position).getTitle());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
