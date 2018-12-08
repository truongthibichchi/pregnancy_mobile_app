package org.a3tn.pregnancy_mobile.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface SummaryInfoService {
    @GET("get-all-summary-info")
    Observable<JsonObject> getAllSummaryInfo();

    @GET("get-summary-info-by-week")
    Observable<JsonObject> getSummaryInfoByWeek(
            @Query("week") String week
    );
}
