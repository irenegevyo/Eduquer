package com.gevyo.android.RuangBacaFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gevyo.android.eduquer.ArticleAdapter;
import com.gevyo.android.eduquer.Artikel;
import com.gevyo.android.eduquer.AuthorAdapter;
import com.gevyo.android.eduquer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RuangBacaHomeFragment extends Fragment {
    private CardView mView;
    private RecyclerView recyclerView;
    FirebaseDatabase database;
    public List<Article> articleList;
    public List<Author> authorList;
    private ArticleAdapter mArticleAdapter;
    private AuthorAdapter mAuthorAdapter;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
            Article thisItem = articleList.get(position);
            Intent intent = new Intent(getActivity(), Artikel.class);
            intent.putExtra("title", thisItem.Title);
            intent.putExtra("author", thisItem.getAuthor());
            intent.putExtra("content", thisItem.Content);
            intent.putExtra("datetime", thisItem.Created_At);
            startActivity(intent);
            Toast.makeText(getContext(), "You Clicked: " + thisItem.Title, Toast.LENGTH_SHORT).show();
        }
    };


    public RuangBacaHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ruang_baca_home, container, false);

        articleList = new ArrayList<>();
        authorList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);


        database = FirebaseDatabase.getInstance();
//        DatabaseReference artikel = database.getReference("Eduquer").child("Author").child("Auth01").child("Article");
        final DatabaseReference author = database.getReference("Eduquer").child("Author");
//        DatabaseReference artikel = database.getReference("Eduquer").child("Author");
        //DatabaseReference content = artikel.child("Content");
        author.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        Article article = snapshot.getValue(Article.class);
                        Author author = snapshot.getValue(Author.class);
                        if (author != null) {
                            Log.d("AUTHOR LOG", author.Name);
                            authorList.add(author);
                            HashMap<String, Article> mArticle = author.articles;
                            for (DataSnapshot dataArticle : snapshot.child("Article").getChildren()) {
                                Article article = dataArticle.getValue(Article.class);
                                article.setAuthor(author.Name);
                                articleList.add(article);
                            }
                        }

                    }
                    mArticleAdapter = new ArticleAdapter(articleList, getContext());
                    recyclerView.setAdapter(mArticleAdapter);
                    mArticleAdapter.setItemClickListener(onItemClickListener);
                    mArticleAdapter.notifyDataSetChanged();

//                    mAuthorAdapter = new AuthorAdapter(authorList, getContext());
//                    recyclerView.setAdapter(mAuthorAdapter);
//                    mAuthorAdapter.notifyDataSetChanged();
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
