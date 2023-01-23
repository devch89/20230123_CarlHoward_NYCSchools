package com.example.a20230123_carlhoward_nycschools.model;

import com.example.a20230123_carlhoward_nycschools.model.presentation.PresentationData;

import io.reactivex.rxjava3.core.Single;

public interface Repository {
    Single<PresentationData> getHSchoolList();
    Single<PresentationData> getHSchoolDetails(String input);
}