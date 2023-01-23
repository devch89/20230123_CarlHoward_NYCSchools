package com.example.a20230123_carlhoward_nycschools.model;

import com.example.a20230123_carlhoward_nycschools.model.presentation.PresentationData;

import java.util.List;

public class HighSchoolSuccessResponse extends PresentationData {
    private List<HighSchoolResponse> data;

    public List<HighSchoolResponse> getData() {
        return data;
    }

    public void setData(List<HighSchoolResponse> data) {
        this.data = data;
    }
}
