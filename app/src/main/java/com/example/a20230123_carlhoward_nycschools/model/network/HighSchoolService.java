package com.example.a20230123_carlhoward_nycschools.model.network;

import com.example.a20230123_carlhoward_nycschools.model.HighSchoolItem;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolSATScoresItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface HighSchoolService {
    // base URL = https://data.cityofnewyork.us/resource/
    public final static String BASE_URL = "https://data.cityofnewyork.us/resource/";
    static String SCHOOLS = "s3k6-pzi2.json";
    static String SCORES = "f9bf-2cp4.json";



    @GET(SCHOOLS)
    public Single<List<HighSchoolItem>> getSchools();

    @GET(SCORES)
    public Single<List<HighSchoolSATScoresItem>> getSatScores();
}

