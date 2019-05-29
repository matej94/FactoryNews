package com.example.factorynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_description) TextView descriptionTv;
    @BindView(R.id.detail_title) TextView titleTv;
    @BindView(R.id.detail_poster) ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();
    }
    private void getIncomingIntent() {

        if (getIntent().hasExtra("news_poster") && getIntent().hasExtra("description") && getIntent().hasExtra("title")) {
            String imageUrl = getIntent().getStringExtra("news_poster");
            String desc = getIntent().getStringExtra("description");
            String title = getIntent().getStringExtra("title");
            setImage(imageUrl, desc, title);
        }
    }
    private void setImage(String imageUrl, String desc, String title){
        descriptionTv.setText(desc);
        titleTv.setText(title);
        Picasso.with(this)
                .load(imageUrl)
                .into(image);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
