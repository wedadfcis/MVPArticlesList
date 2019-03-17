package com.example.mvptask.data.remote;

import android.Manifest;
import android.support.annotation.RequiresPermission;

import com.example.mvptask.common.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceHelper {

    private static volatile Retrofit sRetrofit = null;
    private static ApiInterface apiInterface;

    public ServiceHelper() {
    }

    public static ApiInterface getApiService() {
        return initRetrofitService();
    }

    private static ApiInterface initRetrofitService() {
        if (apiInterface == null) {
            synchronized (ServiceHelper.class) {
                if (apiInterface == null) {
                    apiInterface = getRetrofit().create(ApiInterface.class);
                }
            }
        }
        return apiInterface;
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    private synchronized static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            synchronized (ServiceHelper.class) {
                if (sRetrofit == null) {
                    Gson gson = new GsonBuilder().setLenient().create();
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(Constants.ApiConstants.BASE_URL)
                            .client(createClient())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                }
            }
        }
        return sRetrofit;
    }

    private static OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .readTimeout(Constants.ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}
