package com.pace.lumbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class arrayAdapter extends ArrayAdapter<Cards> {
    Context context;

    public arrayAdapter(Context context, int resourceID, List<Cards> items){
        super(context, resourceID, items);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Cards card_item = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        ImageView profMatch = (ImageView) convertView.findViewById(R.id.imageMatch);
        TextView nameTXT = (TextView) convertView.findViewById(R.id.nameMatch);
        TextView firmTXT = (TextView) convertView.findViewById(R.id.LawFirmName);
        TextView emailTXT = (TextView) convertView.findViewById(R.id.emailMatch);
        TextView phoneTXT = (TextView) convertView.findViewById(R.id.phoneMatch);
        TextView addressTXT = (TextView) convertView.findViewById(R.id.AddressMatch);
        TextView topicTXT = (TextView) convertView.findViewById(R.id.TopicMatch);
        TextView webTXT = (TextView) convertView.findViewById(R.id.WebLinkMatch);

        profMatch.setImageResource(R.mipmap.ic_launcher);
        nameTXT.setText(card_item.getName());
        firmTXT.setText(card_item.getLawFirm());
        emailTXT.setText(card_item.getEmail());
        phoneTXT.setText(card_item.getPhone());
        addressTXT.setText(card_item.getAddress());
        topicTXT.setText(card_item.getTopic());
        webTXT.setText(card_item.getWebsite());

        return convertView;
    }
}