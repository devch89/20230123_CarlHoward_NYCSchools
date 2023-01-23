package com.example.a20230123_carlhoward_nycschools.model.network;

import com.example.a20230123_carlhoward_nycschools.model.HighSchoolItem;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolSATScoresItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class HighSchoolRepositoryImpl implements HighSchoolRepository {

    private HighSchoolService service;

    public HighSchoolRepositoryImpl(HighSchoolService service) {
        this.service = service;
    }

    @Override
    public Single<List<HighSchoolItem>> getAllSchools() {
        return null;
    }

    @Override
    public Single<List<HighSchoolSATScoresItem>> getAllScores() {
        return service.getSatScores();
    }
}