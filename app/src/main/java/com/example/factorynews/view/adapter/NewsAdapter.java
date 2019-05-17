package com.example.factorynews.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.factorynews.R;
import com.example.factorynews.model.News;
import com.squareup.picasso.Picasso;

import io.realm.RealmResults;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private RealmResults<News> realmList;

    public NewsAdapter(RealmResults<News> realmList) {
        this.realmList = realmList;
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
    }

    @Override
    public int getItemCount() {
        return realmList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView AuthorTv, TitleTv, DescriptionTv, URLTv, PublishedAtTv;
        ImageView imgPoster;

        NewsViewHolder(View itemView) {
            super(itemView);
            // AuthorTv = (TextView) itemView.findViewById(R.id.author_tv);
            TitleTv = (TextView) itemView.findViewById(R.id.title_tv);
            //DescriptionTv = (TextView) itemView.findViewById(R.id.description_tv);
            //URLTv = (TextView) itemView.findViewById(R.id.url_tv);
            //PublishedAtTv = (TextView) itemView.findViewById(R.id.publishedAt_tv);
            imgPoster = (ImageView) itemView.findViewById(R.id.img_poster);

        }
    }
}
