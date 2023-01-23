package com.example.a20230123_carlhoward_nycschools.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.a20230123_carlhoward_nycschools.R;
import com.example.a20230123_carlhoward_nycschools.databinding.HschoolsDetailsFragmentLayoutBinding;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolSATResponse;
import com.example.a20230123_carlhoward_nycschools.model.HighSchoolSATSuccessResponse;
import com.example.a20230123_carlhoward_nycschools.model.Repository;
import com.example.a20230123_carlhoward_nycschools.model.presentation.Error;
import com.example.a20230123_carlhoward_nycschools.model.presentation.PresentationData;
import com.example.a20230123_carlhoward_nycschools.viewmodel.HighSchoolVMProvider;
import com.example.a20230123_carlhoward_nycschools.viewmodel.HighSchoolViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HighSchoolDetails extends Fragment {

    @Inject
    Repository repository;

    @Inject
    HighSchoolVMProvider hSchoolViewModelProvider;

    private static final String KEY_DBN = "KEY_DBN_SCHOOL_DETAILS";
    private static final String KEY_NAME = "KEY_NAME_SCHOOL_DETAILS";
    private static final String KEY_LOC = "KEY_LOC_SCHOOL_DETAILS";
    private static final String KEY_PHONE = "KEY_PHONE_SCHOOL_DETAILS";
    private static final String KEY_EMAIL = "KEY_EMAIL_SCHOOL_DETAILS";


    private HschoolsDetailsFragmentLayoutBinding binding;
    private HighSchoolViewModel viewModel;
    private String hSchoolNameStr;
    private String hSchoolLocStr;
    private String hSchoolEmailStr;
    private String hSchoolPhoneStr;
    private static HighSchoolDetails instance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = HschoolsDetailsFragmentLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initObservables();

        if (getArguments().getString(KEY_DBN) != null)
            viewModel.getSatDetails(getArguments().getString(KEY_DBN));

        if (getArguments().getString(KEY_NAME) != null)
            hSchoolNameStr = getArguments().getString(KEY_NAME);

        if (getArguments().getString(KEY_LOC) != null)
            hSchoolLocStr = getArguments().getString(KEY_LOC).substring(0, (getArguments().getString(KEY_LOC).indexOf('(')));

        if (getArguments().getString(KEY_PHONE) != null)
            hSchoolPhoneStr = getArguments().getString(KEY_PHONE);

        if (getArguments().getString(KEY_EMAIL) != null)
            hSchoolEmailStr = getArguments().getString(KEY_EMAIL);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initObservables() {
        viewModel = new ViewModelProvider(this, hSchoolViewModelProvider).get(HighSchoolViewModel.class);
        viewModel.hSchoolState().observe(getViewLifecycleOwner(), this::updateUI);
    }

    private void updateUI(PresentationData presentationData) {
        if (presentationData instanceof HighSchoolSATSuccessResponse)
            updateView(((HighSchoolSATSuccessResponse) presentationData).getData());
        else if (presentationData instanceof Error)
            showError(((Error) presentationData).getErrorMessage());
    }

    private void showError(String errorMessage) {
    }

    private void updateView(HighSchoolSATResponse data) {
        String takers = getString(R.string.sat_takers) + " " + data.getNum_of_sat_test_takers();
        String math = getString(R.string.sat_math) + " "+ data.getSat_math_avg_score();
        String read = getString(R.string.sat_read) + " " + data.getSat_critical_reading_avg_score();
        String write = getString(R.string.sat_write) + " " + data.getSat_writing_avg_score();

        binding.hschoolName.setText(hSchoolNameStr);
        binding.hschoolLocation.setText(hSchoolLocStr);
        binding.hschoolEmail.setText(hSchoolEmailStr);
        binding.hschoolPhone.setText(hSchoolPhoneStr);


        if(data.getDbn() == null || data.getSchool_name() == null || data.getNum_of_sat_test_takers() == null
                || data.getSat_math_avg_score() == null || data.getSat_writing_avg_score() == null || data.getSat_critical_reading_avg_score() == null){
            binding.satDetails.setText(R.string.sat_na);
        }
        else {
            binding.satDetails.setText(R.string.sat_details);
            binding.hschoolDetailsSatTakers.setText(takers);
            binding.hschoolDetailsSatReading.setText(read);
            binding.hschoolDetailsSatWriting.setText(write);
            binding.hschoolDetailsSatMath.setText(math);
        }
    }

    public static HighSchoolDetails getInstance(String dbn, String name, String loc, String email, String phone) {
        HighSchoolDetails fragment = new HighSchoolDetails();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DBN, dbn);
        bundle.putString(KEY_NAME, name);
        bundle.putString(KEY_LOC, loc);
        bundle.putString(KEY_EMAIL, email);
        bundle.putString(KEY_PHONE, phone);
        fragment.setArguments(bundle);
        return fragment;
    }
}