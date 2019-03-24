package com.example.mvptask.view.ui.articles.list;

import com.example.mvptask.base.BaseView;
import com.example.mvptask.data.model.Article;

import java.util.List;

public class ArticlesContract {

    public interface View extends BaseView {
        public void displayArticleList(List<Article> articles);

        public void displayFilterArticleList(List<Article> filterList);

    }

    public interface Presenter {

        public void getArticles();

        public void onFilterArticles(String query);


    }

}
