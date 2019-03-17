package com.example.mvptask.view.ui.details;

import com.example.mvptask.base.BasePresenter;
import com.example.mvptask.base.BaseView;
import com.example.mvptask.data.model.Article;

public interface DetailsContract {

    public interface View extends BaseView<Presenter>
    {
        public void onStart(Article article);
        public void openWebsiteFromUrl(String url);
    }
    public interface  Presenter extends BasePresenter
    {
        public void onArticleSelected();
        public void onOpenUrl();
    }
}
