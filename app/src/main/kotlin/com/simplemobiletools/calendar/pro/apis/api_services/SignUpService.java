package com.simplemobiletools.calendar.pro.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface SignUpService {
    @GET("get-user-sign-up")
    Observable<JsonObject> getUserSignUp(
            @Query("email") String email,
            @Query("password") String password,
            @Query("fullname") String fullname,
            @Query("conceivedDate") String conceivedDate
    );
}
