package com.example.factorynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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

        TextView description = findViewById(R.id.detail_description);
        description.setText(desc);

        ImageView image = findViewById(R.id.detail_poster);
        Picasso.with(this)
                .load(imageUrl)
                .into(image);
    }
}
