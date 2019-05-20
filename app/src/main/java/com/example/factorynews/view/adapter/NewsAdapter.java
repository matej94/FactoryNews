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

import com.example.factorynews.DetailActivity;
import com.example.factorynews.R;
import com.example.factorynews.model.News;
import com.squareup.picasso.Picasso;

import io.realm.RealmResults;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private RealmResults<News> realmList;
    private Context mContext;

    public NewsAdapter(Context context,RealmResults<News> realmList) {
        this.realmList = realmList;
        this.mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder,final int position) {
        // holder.AuthorTv.setText(realmList.get(position).getAuthor());
        holder.TitleTv.setText(realmList.get(position).getTitle());
        //holder.DescriptionTv.setText(realmList.get(position).getDescription());
        //holder.URLTv.setText(realmList.get(position).getUrl());
        //holder.PublishedAtTv.setText(realmList.get(position).getPublishedAt());
        Picasso.with(holder.itemView.getContext()).load(realmList.get(position).getUrlToImage()).into(holder.imgPoster);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("news_poster", realmList.get(position).getUrlToImage());
                intent.putExtra("description", realmList.get(position).getDescription());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return realmList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView AuthorTv, TitleTv, DescriptionTv, URLTv, PublishedAtTv;
        ImageView imgPoster;
        CardView parentLayout;


        NewsViewHolder(View itemView) {
            super(itemView);
            // AuthorTv = (TextView) itemView.findViewById(R.id.author_tv);
            TitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            //DescriptionTv = (TextView) itemView.findViewById(R.id.description_tv);
            //URLTv = (TextView) itemView.findViewById(R.id.url_tv);
            //PublishedAtTv = (TextView) itemView.findViewById(R.id.publishedAt_tv);
            imgPoster = (ImageView) itemView.findViewById(R.id.img_poster);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}
