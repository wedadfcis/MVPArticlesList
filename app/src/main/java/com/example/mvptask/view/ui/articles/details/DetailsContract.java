package com.example.mvptask.view.ui.articles.details;

import com.example.mvptask.base.BaseView;
import com.example.mvptask.data.model.Article;

public interface DetailsContract {

    public interface View extends BaseView {
        public void updateUI(Article article);

        public void openWebsiteFromUrl(String url);
    }

    public interface Presenter {
        public void loadArticle();

        public void onOpenUrl();
    }
}
