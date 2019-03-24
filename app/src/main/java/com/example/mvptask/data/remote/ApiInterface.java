package com.example.mvptask.data.remote;


import com.example.mvptask.data.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("v1/articles?")
    Call<ArticleResponse> getProjectList(@Query("source") String source, @Query("apiKey") String apiKey);
}
