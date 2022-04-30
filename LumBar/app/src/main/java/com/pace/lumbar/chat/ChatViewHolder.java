package com.pace.lumbar.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pace.lumbar.R;

public class ChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mMessage;
    private LinearLayout mContainer;

    public ChatViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);

        mMessage = itemView.findViewById(R.id.chatMessage);
        mContainer = itemView.findViewById(R.id.container);

    }

    public LinearLayout getmContainer() {
        return mContainer;
    }

    public TextView getmMessage() {
        return mMessage;
    }

    public void setmContainer(LinearLayout mContainer) {
        this.mContainer = mContainer;
    }

    public void setmMessage(TextView mMessage) {
        this.mMessage = mMessage;
    }

    @Override
    public void onClick(View view) {

    }
}
