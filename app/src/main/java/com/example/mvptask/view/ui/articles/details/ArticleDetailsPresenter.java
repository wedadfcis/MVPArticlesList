package com.example.mvptask.view.ui.articles.details;

import com.example.mvptask.data.model.dto.Article;

public class ArticleDetailsPresenter implements DetailsContract.Presenter {
    private Article article;
    private DetailsContract.View mArticleView;

    public ArticleDetailsPresenter(Article article, DetailsContract.View mArticleView) {
        this.article = article;
        this.mArticleView = mArticleView;
    }


    @Override
    public void loadArticle() {
        mArticleView.updateUI(article);
    }

    @Override
    public void onOpenUrl() {
        mArticleView.openWebsiteFromUrl(article.getUrl());
    }


}
