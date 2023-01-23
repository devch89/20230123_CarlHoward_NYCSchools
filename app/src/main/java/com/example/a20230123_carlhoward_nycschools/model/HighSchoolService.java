package com.example.a20230123_carlhoward_nycschools.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface HighSchoolService {
    @GET(ENDPOINTS.ENDPOINT_SCHOOL)
    Single<List<HighSchoolResponse>> getHSchoolList();

    @GET(ENDPOINTS.ENDPOINT_SAT)
    Single<List<HighSchoolSATResponse>> getSatList();
}
