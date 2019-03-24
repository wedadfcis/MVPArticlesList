package com.example.mvptask.data.model;

import java.util.List;

public class ArticleResponse {
    private List<Article> articles;
    private String message;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
