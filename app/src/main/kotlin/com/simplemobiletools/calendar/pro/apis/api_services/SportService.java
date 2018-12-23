package com.simplemobiletools.calendar.pro.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import rx.Observable;

public interface SportService {
    @GET("get-all-sport-info")
    Observable<JsonObject> getAllSportInfo();
}
