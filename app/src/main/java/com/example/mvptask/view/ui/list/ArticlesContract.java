package com.example.mvptask.view.ui.list;

import com.example.mvptask.base.BaseModel;
import com.example.mvptask.base.BasePresenter;
import com.example.mvptask.base.BaseView;
import com.example.mvptask.data.model.Article;

import java.util.List;

public class ArticlesContract {

    public interface View extends BaseView<Presenter> {
        public void showProgressBar();

        public void hidProgressBar();

        public void displayArticleList(List<Article> articles);

        public void showErrorMessage(String message);

        public void displayFilterArticleList(List<Article> filterList);

        public void onArticleClick(Article article);
    }

    public interface Presenter extends BasePresenter {

        public void onFetchArticlesFromServer();

        public void onFilterArticles(String query);

        public void onItemClick(Article article);

    }

    public interface PresenterModel extends BaseModel {


    }
}
