package com.gevyo.android.RuangBacaFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gevyo.android.eduquer.ArticleAdapter;
import com.gevyo.android.eduquer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RuangBacaHomeFragment extends Fragment {
    private CardView mView;
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    public List<Article> articleList;
    private ArticleAdapter adapter;


    public RuangBacaHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ruang_baca_home, container, false);

        articleList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

        database = FirebaseDatabase.getInstance();
        DatabaseReference artikel = database.getReference("Eduquer").child("Author").child("Auth01").child("Article").child("Art01");
        DatabaseReference content = artikel.child("Content");
        artikel.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Article article = snapshot.getValue(Article.class);
                        articleList.add(article);
                    }
                    adapter = new ArticleAdapter(articleList, getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
