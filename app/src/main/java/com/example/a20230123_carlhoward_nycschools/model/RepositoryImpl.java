package com.example.a20230123_carlhoward_nycschools.model;

import com.example.a20230123_carlhoward_nycschools.model.presentation.PresentationData;

import io.reactivex.rxjava3.core.Single;

public class RepositoryImpl implements Repository {
    private Network network;

    public RepositoryImpl(Network network){
        this.network = network;
    }

    @Override
    public Single<PresentationData> getHSchoolList() {
        return network.getSERVICE().getHSchoolList()
                .map(hSchoolResponses -> {
                    HighSchoolSuccessResponse result = new HighSchoolSuccessResponse();
                    result.setData(hSchoolResponses);
                    return result;
                });
    }

    @Override
    public Single<PresentationData> getHSchoolDetails(String dbn) {
        return network.getSERVICE().getSatList()
                .map(hSchoolSats -> {
                    HighSchoolSATResponse satSchool = new HighSchoolSATResponse();
                    for (HighSchoolSATResponse sat :
                            hSchoolSats) {
                        if (sat.getDbn().equals(dbn))
                            satSchool = sat;
                    }
                    HighSchoolSATSuccessResponse result = new HighSchoolSATSuccessResponse();
                    result.setData(satSchool);
                    return result;
                });
    }
}
