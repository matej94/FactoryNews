package com.example.factorynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_description) TextView description;
    @BindView(R.id.detail_poster) ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        getIncomingIntent();
    }
    private void getIncomingIntent() {

        if (getIntent().hasExtra("news_poster") && getIntent().hasExtra("description")) {
            String imageUrl = getIntent().getStringExtra("news_poster");
            String desc = getIntent().getStringExtra("description");
            setImage(imageUrl, desc);
        }
    }
    private void setImage(String imageUrl, String desc){
        description.setText(desc);
        Picasso.with(this)
                .load(imageUrl)
                .into(image);
    }
}
