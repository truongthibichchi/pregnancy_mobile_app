package com.simplemobiletools.calendar.pro.apis;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiFactory {
    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(endPoint)
                .build();

        return retrofit.create(clazz);
    }
}
