package com.example.mvptask.view.ui.articles.list;

import android.content.Context;

import com.example.mvptask.R;
import com.example.mvptask.base.BaseModel;
import com.example.mvptask.data.DataManger;
import com.example.mvptask.data.model.Status;
import com.example.mvptask.data.model.dto.Article;
import com.example.mvptask.data.model.dto.ArticleResponse;
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
    public void validateArticleResponse(Status<ArticleResponse> articleResponseStatus) {

        switch (articleResponseStatus.getStatusCode()) {

            case SUCCESS:
                mArticlesView.displayArticleList((List<Article>) articleResponseStatus.getData().getArticles());
                articleList = (List<Article>) articleResponseStatus.getData().getArticles();
                break;
            case ERROR:
                mArticlesView.showErrorMessage(articleResponseStatus.getMessage());
                break;
            case NO_DATA:
                mArticlesView.showErrorMessage(context.getResources().getString(R.string.no_data));
                break;
            case SERVER_ERROR:
                mArticlesView.showErrorMessage(context.getResources().getString(R.string.error_retrieve));
                break;
            default:
                mArticlesView.showErrorMessage(context.getResources().getString(R.string.something_wrong));


        }
    }

    @Override
    public <T> void onDataFetched(T articles) {
        mArticlesView.hidProgressBar();
        Object o = articles;
        Status<ArticleResponse> articleResponse = (Status<ArticleResponse>) o;
        validateArticleResponse(articleResponse);
    }

}

