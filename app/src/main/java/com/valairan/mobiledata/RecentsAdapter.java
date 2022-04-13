package com.valairan.mobiledata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.viewHolder> {

    private ArrayList<RecentTranslation> mList;

    public static class viewHolder extends RecyclerView.ViewHolder {

        public TextView targetLang;
        public TextView sourceLang;
        public TextView sourcePhrase;
        public TextView targetPhrase;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            sourceLang = itemView.findViewById(R.id.sourceLanguage);
            targetLang = itemView.findViewById(R.id.targetLanguage);
            sourcePhrase = itemView.findViewById(R.id.sourceWord);
            targetPhrase = itemView.findViewById(R.id.targetWord);

        }
    }

    public RecentsAdapter(ArrayList<RecentTranslation> list) {
        mList = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recent_translation, parent, false);
        viewHolder evh = new viewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RecentTranslation temp = mList.get(position);
        holder.sourceLang.setText(temp.getSourceLang());
        holder.targetLang.setText(temp.getTargetLang());
        holder.sourcePhrase.setText(temp.getSourcePhrase());
        holder.targetPhrase.setText(temp.getTargetPhrase());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
}
