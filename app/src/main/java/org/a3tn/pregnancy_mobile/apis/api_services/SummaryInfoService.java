package org.a3tn.pregnancy_mobile.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface SummaryInfoService {
    @GET("get-all-summary-info")
    Observable<JsonObject> getAllSummaryInfo();

    @GET("get-baby-info-by-week")
    Observable<JsonObject> getBabyInfoByWeek(
            @Query("week") int weekId
    );

    @GET("get-mom-info-by-week")
    Observable<JsonObject> getMomInfoByWeek(
            @Query("week") int weekId
    );

    @GET("get-symptom-by-week")
    Observable<JsonObject> getSymptomByWeek(
            @Query("week") int weekId
    );

    @GET("get-advice-by-week")
    Observable<JsonObject> getAdviceByWeek(
            @Query("week") int weekId
    );

    @GET("get-all-glossary")
    Observable<JsonObject> getAllGlossary();
}
