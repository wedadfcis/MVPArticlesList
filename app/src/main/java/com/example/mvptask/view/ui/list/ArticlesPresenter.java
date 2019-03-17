package com.example.mvptask.view.ui.list;

import android.content.Context;

import com.example.mvptask.R;
import com.example.mvptask.common.Utilities;
import com.example.mvptask.data.DataManger;
import com.example.mvptask.data.model.Article;
import com.example.mvptask.data.model.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class ArticlesPresenter implements ArticlesContract.Presenter ,ArticlesContract.PresenterModel {

    private final ArticlesContract.View mArticlesView;
    private Context context;
    private List<Article> articleList;
    private DataManger dataManger;

    public ArticlesPresenter(ArticlesContract.View mArticlesView, Context context) {
        this.mArticlesView = mArticlesView;
        this.context = context;
        mArticlesView.setPresenter(this);
    }

    @Override
    public void onFetchArticlesFromServer() {
        dataManger = new DataManger(this);
        if (Utilities.checkIfApplicationIsConnected(context)) {
            dataManger.getArticleList(context);
            mArticlesView.showProgressBar();
        } else {
            mArticlesView.showErrorMessage(context.getResources().getString(R.string.networkErrorCode));
        }
    }

    @Override
    public void onFilterArticles(String query) {
        List<Article> filteredList = new ArrayList<>();
        filteredList.clear();
        if(articleList != null) {
            if (!query.isEmpty() || !query.equals("")) {
                for (Article currentArticle :articleList) {
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
    public void onItemClick(Article article) {
        mArticlesView.onArticleClick(article);
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
        articleList =articleResponse.getArticles();
    }

}

