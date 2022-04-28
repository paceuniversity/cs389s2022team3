package com.pace.lumbar.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pace.lumbar.R;

public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ChatViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
