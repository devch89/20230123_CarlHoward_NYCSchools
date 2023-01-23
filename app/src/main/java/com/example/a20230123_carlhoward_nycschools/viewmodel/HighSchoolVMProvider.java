package com.example.a20230123_carlhoward_nycschools.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.a20230123_carlhoward_nycschools.model.Repository;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;

public class HighSchoolVMProvider implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public HighSchoolVMProvider(Repository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HighSchoolViewModel(repository);
    }
}