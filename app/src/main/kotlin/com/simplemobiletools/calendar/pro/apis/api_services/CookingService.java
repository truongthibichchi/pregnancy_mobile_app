package com.simplemobiletools.calendar.pro.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CookingService {
    @GET("get-all-cooking-info")
    Observable<JsonObject> getAllCookingInfo();

    @GET("get-cooking-ingredient-by-cooking-id")
    Observable<JsonObject> getCookingIngredientByCookingId(@Query("cookingId") int cookingId);

    @GET("get-cooking-step-by-cooking-id")
    Observable<JsonObject> getCookingStepByCookingId(@Query("cookingId") int cookingId);


    @GET("get-cooking-tip-by-cooking-id")
    Observable<JsonObject> getCookingTipByCookingId(@Query("cookingId") int cookingId);


}
