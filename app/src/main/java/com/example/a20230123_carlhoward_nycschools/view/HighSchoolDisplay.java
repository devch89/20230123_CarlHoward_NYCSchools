package com.example.a20230123_carlhoward_nycschools.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a20230123_carlhoward_nycschools.databinding.HschoolDisplayFragmentLayoutBinding;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolResponse;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolSuccessResponse;
import com.example.a20230123_carlhoward_nycschools.model.Repository;
import com.example.a20230123_carlhoward_nycschools.model.presentation.Error;
import com.example.a20230123_carlhoward_nycschools.model.presentation.PresentationData;
import com.example.a20230123_carlhoward_nycschools.viewmodel.HighSchoolVMProvider;
import com.example.a20230123_carlhoward_nycschools.viewmodel.HighSchoolViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HighSchoolDisplay extends Fragment implements Listeners.ListClickEvent{

    @Inject
    Repository repository;
    @Inject
    HighSchoolVMProvider schoolViewModelProvider;

    private HschoolDisplayFragmentLayoutBinding binding;
    private HighSchoolViewModel viewModel;
    private HighSchoolAdapter adapter;
    private Listeners listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Listeners)
            listener = (Listeners) context;
        else
            throw new ExceptionInInitializerError("Incorrect Host Activity");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = HschoolDisplayFragmentLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.hschoolList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        initObservables();
        return view;
    }

    private void initObservables() {
        viewModel = new ViewModelProvider(this, schoolViewModelProvider).get(HighSchoolViewModel.class);
        viewModel.hSchoolState().observe(getViewLifecycleOwner(), this::processUIState);
    }

    private void processUIState(PresentationData presentationData) {
        if (presentationData instanceof HighSchoolSuccessResponse)
            updateAdapter(((HighSchoolSuccessResponse) presentationData).getData());
        else if (presentationData instanceof Error)
            showError(((Error) presentationData).getErrorMessage());
    }

    private void showError(String errorMessage) {

    }

    private void updateAdapter(List<HighSchoolResponse> data) {
        adapter = new HighSchoolAdapter(data, this);
        binding.hschoolList.setAdapter(adapter);
    }

    @Override
    public void clickDetails(String dbn, String name, String loc, String email, String phone) {
        listener.openDetails(dbn, name, loc, email, phone);
    }
}
