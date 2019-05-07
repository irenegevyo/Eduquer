package com.gevyo.android.eduquer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gevyo.android.RuangBacaFragment.Author;

import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ArticleViewHolder> {

    List<Author> authors;
    private Context context;

    public AuthorAdapter(List<Author> authors, Context context) {
        this.authors = authors;
        this.context = context;
    }

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView artikelbunga;
        TextView Datearticle;
        TextView Author;
        ImageView ruangbaca1;

        ArticleViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            artikelbunga = (TextView) itemView.findViewById(R.id.artikelbunga);
            Datearticle = (TextView) itemView.findViewById(R.id.DateArticle);
            Author = (TextView) itemView.findViewById(R.id.Author);
            ruangbaca1 = (ImageView) itemView.findViewById(R.id.ruang_baca1);
        }
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_item, viewGroup, false);
        ArticleViewHolder avh = new ArticleViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int i) {
        holder.artikelbunga.setText(authors.get(i).Name);
        holder.Datearticle.setText(authors.get(i).Facebook);

    }

}