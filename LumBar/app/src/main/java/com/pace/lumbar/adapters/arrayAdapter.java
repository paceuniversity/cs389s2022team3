package com.pace.lumbar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

        ImageView profMatch = (ImageView) convertView.findViewById(R.id.image);
        TextView nameTXT = (TextView) convertView.findViewById(R.id.nameMatch);
        TextView firmTXT = (TextView) convertView.findViewById(R.id.LawFirmName);
        TextView emailTXT = (TextView) convertView.findViewById(R.id.emailMatch);
        TextView phoneTXT = (TextView) convertView.findViewById(R.id.phoneMatch);
        TextView addressTXT = (TextView) convertView.findViewById(R.id.AddressMatch);
        TextView topicTXT = (TextView) convertView.findViewById(R.id.TopicMatch);
        TextView webTXT = (TextView) convertView.findViewById(R.id.WebLinkMatch);

        switch(card_item.getProfileIMGUri()) {
            case "default":
                Glide.with(convertView.getContext()).load(R.mipmap.ic_launcher).into(profMatch);
                break;
            default:
                Glide.with(convertView.getContext()).load(card_item.getProfileIMGUri()).into(profMatch);
                break;

        }

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
