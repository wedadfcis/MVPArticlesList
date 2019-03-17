package com.example.mvptask.data;

import android.content.Context;
import com.example.mvptask.R;
import com.example.mvptask.data.model.ArticleResponse;
import com.example.mvptask.data.remote.ServiceHelper;
import com.example.mvptask.common.Constants;
import com.example.mvptask.view.ui.list.ArticlesContract;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class DataManger  {
    ArticlesContract.PresenterModel presenterModel;
    public DataManger( ArticlesContract.PresenterModel modelContract) {
        this.presenterModel = modelContract;
    }
    public ArticleResponse getArticleList(final Context context) {
        final ArticleResponse articleResponse = new ArticleResponse();
        ServiceHelper.getApiService().getProjectList(Constants.ApiConstants.SOURCE,Constants.ApiConstants.API_KEY).enqueue(new Callback<ArticleResponse>() {

            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.body().getArticles() != null) {
                    articleResponse.setArticles(response.body().getArticles());
                    articleResponse.setMessage(Constants.ResponseStatus.SUCCESS);
                    presenterModel.onDataFetched(articleResponse);
                }else
                {
                    articleResponse.setArticles(null);
                    articleResponse.setMessage(context.getResources().getString(R.string.error_retrieve));
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                articleResponse.setArticles(null);
                articleResponse.setMessage(getCustomErrorMessage(t,context));
                presenterModel.onDataFetched(articleResponse);
            }
        });
        return articleResponse;
    }

    public String getCustomErrorMessage(Throwable error,Context context) {

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
