package com.example.mvptask.view.ui.details;

import com.example.mvptask.data.model.Article;

public class ArticleDetailsPresenter implements DetailsContract.Presenter{
    private Article article;
    private DetailsContract.View mArticleView;

    public ArticleDetailsPresenter(Article article, DetailsContract.View mArticleView) {
        this.article = article;
        this.mArticleView = mArticleView;
        mArticleView.setPresenter(this);
    }


    @Override
    public void onArticleSelected() {
        mArticleView.onStart(article);
    }

    @Override
    public void onOpenUrl() {
        mArticleView.openWebsiteFromUrl(article.getUrl());
    }


}
