package com.example.mvptask.view.ui.articles.list;

import android.content.Context;

import com.example.mvptask.R;
import com.example.mvptask.base.BaseModel;
import com.example.mvptask.data.DataManger;
import com.example.mvptask.data.model.Article;
import com.example.mvptask.data.model.ArticleResponse;
import com.example.mvptask.helper.Utilities;

import java.util.ArrayList;
import java.util.List;

public class ArticlesPresenter implements ArticlesContract.Presenter, BaseModel {

    private final ArticlesContract.View mArticlesView;
    private Context context;
    private List<Article> articleList;
    private DataManger dataManger;

    public ArticlesPresenter(ArticlesContract.View mArticlesView, Context context) {
        this.mArticlesView = mArticlesView;
        this.context = context;
    }

    @Override
    public void getArticles() {
        dataManger = new DataManger(this);
        if (Utilities.checkIfApplicationIsConnected(context)) {
            mArticlesView.showProgressBar();
            dataManger.getArticleList(context);
        } else {
            mArticlesView.showErrorMessage(context.getResources().getString(R.string.networkErrorCode));
        }
    }

    @Override
    public void onFilterArticles(String query) {
        List<Article> filteredList = new ArrayList<>();
        filteredList.clear();
        if (articleList != null) {
            if (!query.isEmpty() || !query.equals("")) {
                for (Article currentArticle : articleList) {
                    // filter with author name
                    if (currentArticle.getAuthor().toLowerCase().contains(query)) {
                        filteredList.add(currentArticle);
                    }
                }
            } else {
                filteredList = articleList;
            }
        }

        mArticlesView.displayFilterArticleList(filteredList);
    }


    @Override
    public <T> void onDataFetched(T Articles) {
        mArticlesView.hidProgressBar();
        Object o = Articles;
        ArticleResponse articleResponse = (ArticleResponse) o;
        if (articleResponse.getArticles() == null) {
            mArticlesView.showErrorMessage(articleResponse.getMessage());
        } else {
            mArticlesView.displayArticleList(articleResponse.getArticles());
        }
        articleList = articleResponse.getArticles();
    }

}

