package com.nuntakaset.siriluk.rmutsvservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nuntakaset.siriluk.rmutsvservice.fragment.MainFragment;

public class RMUTSV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmutsv);

//        Add Fragment to Activity
        if (savedInstanceState == null){

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFragmentMain, new MainFragment()).commit();

        }

    }   // Main Method

}   // Main Class
