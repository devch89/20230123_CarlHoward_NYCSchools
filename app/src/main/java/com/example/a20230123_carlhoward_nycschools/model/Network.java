package com.example.a20230123_carlhoward_nycschools.model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private Network() {
    }

    private static final Object lock = new Object();
    private static Network INSTANCE;
    private HighSchoolService SERVICE;

    public static Network getInstance() {
        if (INSTANCE != null) return INSTANCE;

        synchronized (lock) {
            if (INSTANCE != null) return INSTANCE;

            INSTANCE = new Network();

            return INSTANCE;
        }
    }

    public HighSchoolService getSERVICE() {
        if (SERVICE != null)
            return SERVICE;
        else {
            SERVICE = initRetrofit();
        }
        return SERVICE;
    }

    private HighSchoolService initRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ENDPOINTS.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(HighSchoolService.class);
    }
}

class ENDPOINTS {
    public static final String BASE_URL = "https://data.cityofnewyork.us/";
    public static final String ENDPOINT_SCHOOL = "resource/s3k6-pzi2.json";
    public static final String ENDPOINT_SAT = "resource/f9bf-2cp4.json";
}