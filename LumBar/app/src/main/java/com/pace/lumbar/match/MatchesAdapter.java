package com.pace.lumbar.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pace.lumbar.R;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesViewHolder> {
    private List<MatchesObject> matchesList;
    private Context context;

    public MatchesAdapter(List<MatchesObject>matchesList, Context context){
        this.matchesList = matchesList;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.matchesitems, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        MatchesViewHolder rcv = new MatchesViewHolder(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        holder.getmMatchId().setText(matchesList.get(position).getEmail());
        holder.getmMatchName().setText(matchesList.get(position).getName());
        if(!matchesList.get(position).getProfileIMGUri().toString().equals("default") || !matchesList.get(position).getProfileIMGUri().toString().equals("") || matchesList.get(position).getProfileIMGUri() != null){
            Glide.with(context).load(matchesList.get(position).getProfileIMGUri()).into(holder.getmMatchImg());
        }
        else{
            Glide.with(context).load(R.mipmap.ic_launcher).into(holder.getmMatchImg());
        }
    }

    @Override
    public int getItemCount() {
        return this.matchesList.size();
    }
}
