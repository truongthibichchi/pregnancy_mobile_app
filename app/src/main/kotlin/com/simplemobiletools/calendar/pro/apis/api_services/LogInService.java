package com.simplemobiletools.calendar.pro.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface LogInService {
    @GET("get-user-log-in")
    Observable<JsonObject> getUserLogIn(
            @Query("email") String email,
            @Query("password") String password
    );
}
