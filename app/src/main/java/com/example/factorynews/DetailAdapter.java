package com.example.factorynews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.factorynews.model.News;
import com.squareup.picasso.Picasso;

import io.realm.RealmResults;

public class DetailAdapter extends PagerAdapter {
    private RealmResults<News> realmList;
    private Context mContext;
    LayoutInflater inflater;

    public DetailAdapter(Context context,RealmResults<News> realmList) {
        this.realmList = realmList;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return realmList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        TextView titleTv;
        TextView descTv;
        ImageView image;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container, false);

        titleTv = itemView.findViewById(R.id.detail_title);
        descTv = itemView.findViewById(R.id.detail_description);

        titleTv.setText(realmList.get(position).getTitle());
        descTv.setText(realmList.get(position).getDescription());
        image = itemView.findViewById(R.id.detail_poster);
        Picasso.with(itemView.getContext()).load(realmList.get(position).getUrlToImage()).into(image);

        // Add viewpager_item.xml to ViewPager
        (container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        (container).removeView((RelativeLayout) object);

    }

}
