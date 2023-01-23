package com.example.a20230123_carlhoward_nycschools.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.a20230123_carlhoward_nycschools.model.HighSchoolSATSuccessResponse;
import com.example.a20230123_carlhoward_nycschools.model.Repository;
import com.example.a20230123_carlhoward_nycschools.model.presentation.PresentationData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HighSchoolViewModel extends ViewModel {

    private MutableLiveData<PresentationData> _hSchoolState = new MutableLiveData<>();
    public LiveData<PresentationData> hSchoolState(){
        return _hSchoolState;
    }
    private final Repository repository;

    @Inject
    public HighSchoolViewModel(Repository repository){
        this.repository = repository;
        init();
    }

    private void init(){
        repository.getHSchoolList()
                .subscribeOn(
                        Schedulers.io()
                )
                .observeOn(
                        AndroidSchedulers.mainThread()
                )
                .subscribe(
                        new SingleObserver<PresentationData>(){
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                // dispose any composable...
                            }
                            @Override
                            public void onSuccess(@NonNull PresentationData presentationData) {
                                _hSchoolState.setValue(presentationData);
                            }
                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        }
                );
    }

    public void getSatDetails(String dbn){
        repository.getHSchoolDetails(dbn)
                .subscribeOn(
                        Schedulers.io()
                )
                .observeOn(
                        AndroidSchedulers.mainThread()
                )
                .subscribe(
                        new SingleObserver<PresentationData>(){

                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                // dispose any composable...
                            }

                            @Override
                            public void onSuccess(@NonNull PresentationData presentationData) {
                                Log.d("TAG", "onSuccess: "+ ((HighSchoolSATSuccessResponse) presentationData).getData().getNum_of_sat_test_takers());
                                _hSchoolState.setValue(presentationData);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                            }
                        }
                );
    }
}