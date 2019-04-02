package com.camellia.soorty.di.modules;



import com.camellia.soorty.BuildConfig;
import com.camellia.soorty.Repos.MyAppPref;
import com.camellia.soorty.di.ApiInterface;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    @Singleton
    @Provides
    public HttpLoggingInterceptor provideLoginIntercepter() {
        HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel);
        return interceptor;
    }

    @Singleton
    @Provides
    public Interceptor provideHeaderIntercepter(MyAppPref myAppPref) {
        return chain -> {
            Request originalRequest = chain.request();
            Request.Builder builder;
            String headerValue;
            if(myAppPref.getAccessToken()!=null){
                headerValue=myAppPref.getAccessToken();
            }else {
                headerValue=BuildConfig.Authorization;
            }
            //Log.e("my pref : "+myAppPref," header value : "+headerValue);
            builder = originalRequest.newBuilder().header("Authorization",headerValue);
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        };
    }


    @Singleton
    @Provides
    public OkHttpClient provideClient(HttpLoggingInterceptor httpLoggingInterceptor,Interceptor headerInterceptor ) {
        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(50000, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(headerInterceptor)
                .build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    public ApiInterface provideApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

}
