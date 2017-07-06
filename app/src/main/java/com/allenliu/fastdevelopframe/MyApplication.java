package com.allenliu.fastdevelopframe;

import android.content.Context;

import com.allenliu.library.BaseApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by allenliu on 2017/7/6.
 */

public class MyApplication extends BaseApplication {
    public static Context globalContext;
    public static ApiService apiService;
    @Override
    public void onCreate() {
        super.onCreate();
        initRetrofit();
        globalContext=this;
    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(interceptor)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }
}
