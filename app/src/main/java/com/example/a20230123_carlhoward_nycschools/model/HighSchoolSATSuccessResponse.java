package com.example.a20230123_carlhoward_nycschools.model;

import com.example.a20230123_carlhoward_nycschools.model.presentation.PresentationData;

public class HighSchoolSATSuccessResponse extends PresentationData {
    private HighSchoolSATResponse data;

    public HighSchoolSATResponse getData() {
        return data;
    }

    public void setData(HighSchoolSATResponse data) {
        this.data = data;
    }
}
