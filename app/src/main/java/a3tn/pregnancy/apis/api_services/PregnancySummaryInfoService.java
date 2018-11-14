package a3tn.pregnancy.apis.api_services;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface PregnancySummaryInfoService {
    @GET("get-all-summary-info")
    Observable<JsonObject> getAllSummaryInfo();

    @GET("get-summary-info-by-title")
    Observable<JsonObject> getSummaryInfoByTitle(
            @Query("title") String cm
    );
}
