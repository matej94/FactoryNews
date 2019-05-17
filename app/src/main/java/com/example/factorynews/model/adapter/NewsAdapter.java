package com.example.factorynews.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.factorynews.R;
import com.example.factorynews.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder,final int position) {
        // holder.AuthorTv.setText(dataList.get(position).getAuthor());
        holder.TitleTv.setText(newsList.get(position).getTitle());
        //holder.DescriptionTv.setText(dataList.get(position).getDescription());
        //holder.URLTv.setText(dataList.get(position).getUrl());
        //holder.PublishedAtTv.setText(dataList.get(position).getPublishedAt());
        Picasso.with(holder.itemView.getContext()).load(newsList.get(position).getUrlToImage()).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
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
