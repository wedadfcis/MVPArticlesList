package com.example.mvptask.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvptask.R;
import com.example.mvptask.data.model.Article;
import com.example.mvptask.view.callback.ArticleClickCallBack;

import java.util.ArrayList;
import java.util.List;


public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {

    private List<Article> articleList;
    private final ArticleClickCallBack articleClickCallBack;
    private Context context;

    public ArticleListAdapter(ArticleClickCallBack articleClickCallBack, Context context) {
        this.articleClickCallBack = articleClickCallBack;
        this.context = context;
        this.articleList = new ArrayList<Article>();

    }

    public void setFilter(final List<Article> filteredArticleList) {
        this.articleList = filteredArticleList;
        notifyDataSetChanged();
    }

    public void setArticleList(final List<Article> articleList) {
        this.articleList = articleList;
        notifyItemRangeInserted(0, articleList.size());


    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article currentArticle = articleList.get(position);
        ImageAndDateLoader.loadImage(holder.imgArticle, currentArticle.getUrlToImage());
        holder.txtArticleTitle.setText(currentArticle.getTitle());
        holder.txtAuthorName.setText(context.getString(R.string.by) + " " + currentArticle.getAuthor());
        ImageAndDateLoader.loadDate(holder.txtPublishDate, currentArticle.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        if (articleList != null)
            return articleList.size();
        else
            return 0;
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView imgArticle;
        TextView txtArticleTitle;
        TextView txtAuthorName;
        TextView txtPublishDate;
        CardView cardArticle;


        public ArticleViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
            cardArticle.setOnClickListener(viewArticleListener);
        }

        private void initViews(View itemView) {
            imgArticle = (ImageView) itemView.findViewById(R.id.article_image);
            txtArticleTitle = (TextView) itemView.findViewById(R.id.article_title);
            txtAuthorName = (TextView) itemView.findViewById(R.id.by);
            txtPublishDate = (TextView) itemView.findViewById(R.id.publish_date);
            cardArticle = (CardView) itemView.findViewById(R.id.article_card_view);
        }

        private View.OnClickListener viewArticleListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (articleClickCallBack != null) {
                    articleClickCallBack.onClick(articleList.get(getAdapterPosition()));
                }
            }
        };
    }
}
