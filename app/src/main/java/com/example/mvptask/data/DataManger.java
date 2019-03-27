package com.example.mvptask.data;

import android.content.Context;

import com.example.mvptask.R;
import com.example.mvptask.data.model.dto.ArticleResponse;
import com.example.mvptask.data.model.Status;
import com.example.mvptask.data.model.StatusCode;
import com.example.mvptask.data.remote.ServiceHelper;
import com.example.mvptask.helper.Constants;
import com.example.mvptask.view.ui.articles.list.ArticlesPresenter;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class DataManger {
    ArticlesPresenter articlesPresenter;

    public DataManger(ArticlesPresenter articlesPresenter) {
        this.articlesPresenter = articlesPresenter;
    }

    public void getArticleList(final Context context) {
        final Status articleResponseStatus = new Status<>();
        ServiceHelper.getApiService().getProjectList(Constants.ApiConstants.SOURCE, Constants.ApiConstants.API_KEY).enqueue(new Callback<ArticleResponse>() {

            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.body() != null) {
                    if (response.body().getArticles() != null && response.body().getArticles().size() > 0) {
                        articleResponseStatus.setData(response.body());
                        articleResponseStatus.setStatusCode(StatusCode.SUCCESS);
                    } else {
                        //no data
                        articleResponseStatus.setData(null);
                        articleResponseStatus.setStatusCode(StatusCode.NO_DATA);
                    }
                } else {
                    //server error
                    articleResponseStatus.setData(null);
                    articleResponseStatus.setStatusCode(StatusCode.SERVER_ERROR);
                }
                articlesPresenter.onDataFetched(articleResponseStatus);
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                //error
                articleResponseStatus.setData(null);
                articleResponseStatus.setMessage(getCustomErrorMessage(t, context));
                articleResponseStatus.setStatusCode(StatusCode.ERROR);
                articlesPresenter.onDataFetched(articleResponseStatus);
            }
        });
    }

    public String getCustomErrorMessage(Throwable error, Context context) {

        if (error instanceof SocketTimeoutException) {
            return context.getString(R.string.requestTimeOutError);
        } else if (error instanceof MalformedJsonException) {
            return context.getResources().getString(R.string.responseMalformedJson);
        } else if (error instanceof IOException) {
            return context.getResources().getString(R.string.networkError);
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return context.getString(R.string.unknownError);
        }

    }

}
