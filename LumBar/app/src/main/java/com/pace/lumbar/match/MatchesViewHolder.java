package com.pace.lumbar.match;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.pace.lumbar.R;
import com.pace.lumbar.chat.ChatActivity;

public class MatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mMatchId, mMatchName;
    private ImageView mMatchImg;

    public MatchesViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);

        mMatchId = (TextView) itemView.findViewById(R.id.MatchID);
        mMatchName = (TextView) itemView.findViewById(R.id.MatchName);
        mMatchImg = (ImageView) itemView.findViewById(R.id.MatchImg);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ChatActivity.class);
        Bundle b = new Bundle();
        b.putString("Match ID", mMatchId.getText().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    }

    public ImageView getmMatchImg() {
        return mMatchImg;
    }

    public TextView getmMatchName() {
        return mMatchName;
    }

    public void setmMatchName(TextView mMatchName) {
        this.mMatchName = mMatchName;
    }

    public void setmMatchImg(ImageView mMatchImg) {
        this.mMatchImg = mMatchImg;
    }

    public TextView getmMatchId() {
        return mMatchId;
    }

    public void setmMatchId(TextView mMatchId) {
        this.mMatchId = mMatchId;
    }
}
