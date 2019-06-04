package com.example.factorynews.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.factorynews.view.DetailActivity;
import com.example.factorynews.R;
import com.example.factorynews.model.News;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private RealmResults<News> realmList;
    private Context mContext;

    // Pass in the news array into the constructor
    public NewsAdapter(Context context,RealmResults<News> realmList) {
        this.realmList = realmList;
        this.mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(NewsViewHolder holder,final int position) {
        // Get element from data at this position and replace the contents of the view with that element
        holder.TitleTv.setText(realmList.get(position).getTitle());
        Picasso.with(holder.itemView.getContext()).load(realmList.get(position).getUrlToImage()).into(holder.imgPoster);

        // Listening for the click in recycler view
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Pass title and position of clicked item to another activity
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("title", realmList.get(position).getTitle());
                intent.putExtra("item_position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return realmList.size();
    }

    // Provide a reference to the views for each data item
     class NewsViewHolder extends RecyclerView.ViewHolder {

         @BindView(R.id.title_tv)TextView TitleTv;
         @BindView(R.id.img_poster)ImageView imgPoster;
         @BindView(R.id.parent_layout)CardView parentLayout;

         NewsViewHolder(View itemView) {
            super(itemView);
           ButterKnife.bind(this, itemView);
        }
    }
}
