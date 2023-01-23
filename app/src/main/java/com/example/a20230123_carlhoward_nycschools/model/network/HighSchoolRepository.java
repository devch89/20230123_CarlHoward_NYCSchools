package com.example.a20230123_carlhoward_nycschools.model.network;

import com.example.a20230123_carlhoward_nycschools.model.HighSchoolItem;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolSATScoresItem;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface HighSchoolRepository {
    Single<List<HighSchoolItem>> getAllSchools();
    Single<List<HighSchoolSATScoresItem>> getAllScores();
}
