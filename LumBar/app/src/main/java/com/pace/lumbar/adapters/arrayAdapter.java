package com.pace.lumbar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pace.lumbar.match.Cards;
import com.pace.lumbar.R;

import java.util.List;

public class arrayAdapter extends android.widget.ArrayAdapter<Cards> {

    Context context;

    public arrayAdapter(Context context, int resourceID, List<Cards> items){
        super(context, resourceID, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Cards card_item = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);

        name.setText(card_item.getName());
        image.setImageResource(R.mipmap.ic_launcher);

        return convertView;
    }

}
