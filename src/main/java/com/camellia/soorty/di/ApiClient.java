package com.camellia.soorty.di;

import android.annotation.SuppressLint;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiClient {

    //Live URL
    public static final String BASE_URL = "http://192.168.1.82/soorty/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().readTimeout(1, TimeUnit.MINUTES).
                connectTimeout(1, TimeUnit.MINUTES).addInterceptor(new Interceptor() {
            @SuppressLint("LongLogTag")
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request = originalRequest.newBuilder()
                        .method(originalRequest.method(), originalRequest.body())
                        .build();
                return chain.proceed(request);
            }
        }).addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
