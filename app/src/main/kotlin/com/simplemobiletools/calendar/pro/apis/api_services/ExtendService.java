package com.simplemobiletools.calendar.pro.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import rx.Observable;

public interface ExtendService {
    @GET("get-all-image-for-baby")
    Observable<JsonObject> getAllImageForBaby();
}
