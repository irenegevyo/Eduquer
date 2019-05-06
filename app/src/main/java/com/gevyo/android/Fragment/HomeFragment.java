package com.gevyo.android.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gevyo.android.eduquer.R;
import com.gevyo.android.eduquer.RuangBacaActivity;
import com.gevyo.android.eduquer.RuangLesActivity;
import com.gevyo.android.eduquer.SessionHandler;
import com.gevyo.android.eduquer.User;

public class HomeFragment extends Fragment {

    TextView welcomeText;
    ImageView openAccount;
    private SessionHandler session;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final Context context = inflater.getContext();
        session = new SessionHandler(context);
        User user = session.getUserDetails();

        welcomeText = view.findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome ");

        openAccount = view.findViewById(R.id.ruang_baca);
        openAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Open Account Fragment", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageView ivRuangBaca = getView().findViewById(R.id.ruang_baca);
        ivRuangBaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getActivity(), RuangBacaActivity.class);
                startActivity(mainIntent);
            }
        });

        ImageView ivRuangLes = getView().findViewById(R.id.ruang_les);
        ivRuangLes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lesIntent = new Intent(getActivity(), RuangLesActivity.class);
                startActivity(lesIntent);
            }
        });
    }
}