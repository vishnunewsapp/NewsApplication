package net.vishnu.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.vishnu.news.R;
import net.vishnu.news.httpapi.model.NewsData;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    List<NewsData> newsDataList;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;
        private TextView description;
        private TextView date;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.news_image);
            title = view.findViewById(R.id.news_title);
            description = view.findViewById(R.id.news_desc);
            date = view.findViewById(R.id.news_date);
        }
    }

    public NewsRecyclerAdapter(List<NewsData> newsDataList, Context context) {
        this.newsDataList = newsDataList;
        this.context = context;
    }

    @Override
    public NewsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsData newsData = newsDataList.get(position);
        Glide.with(context)
                .load(newsData.getUrlToImage())
                .into(holder.imageView);
        holder.title.setText(newsData.getTitle());
        holder.description.setText(newsData.getDescription());
        holder.date.setText(newsData.getPublishedAt().substring(0, newsData.getPublishedAt().indexOf("T")));
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

}
