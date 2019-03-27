package com.example.mvptask.data.model.dto;

import java.util.List;

public class ArticleResponse {
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
