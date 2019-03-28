package com.example.mvptask.view.ui.articles.list;

import android.view.Menu;

import com.example.mvptask.base.BaseView;
import com.example.mvptask.data.model.Status;
import com.example.mvptask.data.model.dto.Article;
import com.example.mvptask.data.model.dto.ArticleResponse;

import java.util.List;

public class ArticlesContract {

    public interface View extends BaseView {

        public void displayArticleList(List<Article> articles);

        public void displayFilterArticleList(List<Article> filterList);

        public void filterArticles(String query);

        public void getArticles();

        public void initializeRecyclerView();

        public void search(Menu menu);

        public void initializeSearchView(Menu menu);


    }

    public interface Presenter {

        public void getArticles();

        public void onFilterArticles(String query);

        public void validateArticleResponse(Status<ArticleResponse> articleResponseStatus);

    }

}
