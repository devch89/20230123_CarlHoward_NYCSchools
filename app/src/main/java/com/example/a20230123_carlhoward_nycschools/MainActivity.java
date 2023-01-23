package com.example.a20230123_carlhoward_nycschools;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a20230123_carlhoward_nycschools.view.HighSchoolDetails;
import com.example.a20230123_carlhoward_nycschools.view.HighSchoolDisplay;
import com.example.a20230123_carlhoward_nycschools.view.Listeners;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements Listeners {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null)
            showDisplayFragment();
    }

    private void showDisplayFragment() {
        HighSchoolDisplay schoolDisplayFragment = new HighSchoolDisplay();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, schoolDisplayFragment)
                .commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("Initialize", false);
    }

    @Override
    public void openDetails(String dbn, String name, String loc, String email, String phone) {

        HighSchoolDetails schoolDetailsFragment = new HighSchoolDetails().getInstance(dbn, name, loc, email, phone);
        //schoolDetailsFragment.setRetainInstance(true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, schoolDetailsFragment)
                .addToBackStack(null)

                .commit();
    }

}