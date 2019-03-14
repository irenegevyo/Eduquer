package com.gevyo.android.eduquer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class RuangLesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruang_les);

        int resID1 = R.drawable.presentation;
        ImageView mFoto1 = findViewById(R.id.biology);
        Glide.with(this)
                .load(resID1)
                .into(mFoto1);

        int resID2 = R.drawable.clock;
        ImageView mFoto2 = findViewById(R.id.chemistry);
        Glide.with(this)
                .load(resID2)
                .into(mFoto2);

        int resID3 = R.drawable.like;
        ImageView mFoto3 = findViewById(R.id.jaminan);
        Glide.with(this)
                .load(resID3)
                .into(mFoto3);

        int resID4 = R.drawable.professor;
        ImageView mFoto4 = findViewById(R.id.guru);
        Glide.with(this)
                .load(resID4)
                .into(mFoto4);

        int resID5 = R.drawable.hourglass;
        ImageView mFoto5 = findViewById(R.id.hourglass);
        Glide.with(this)
                .load(resID5)
                .into(mFoto5);

    }
}
