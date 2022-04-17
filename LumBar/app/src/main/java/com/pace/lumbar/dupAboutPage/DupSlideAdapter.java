package com.pace.lumbar.dupAboutPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.pace.lumbar.R;

public class DupSlideAdapter extends PagerAdapter{

    Context context;
    LayoutInflater inflater;
    private ImageButton button;

    public DupSlideAdapter(Context context){
        this.context = context;
    }

    //list of images
    private int[] img = {
            R.drawable.origin,
            R.drawable.courthouse,
            R.drawable.app
    };

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        TextView txttitle = (TextView) view.findViewById(R.id.txttitle);
        TextView descriptions = (TextView) view.findViewById(R.id.txtdescrip);
        imgslide.setImageResource(img[position]);
        txttitle.setText(titles[position]);
        descriptions.setText(desc[position]);
        container.addView(view);

//        button = button.findViewById(R.id.revert);
//        button.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(v.getContext(), LoginPage.class);
//                context.startActivity(intent);
//            }
//        });

        return view;
    }

    //list of titles
    private String [] titles = {
            "Origin",
            "Mission",
            "Developers"
    };

    //list of descriptions
    private String[] desc = {
            "LumBar is an in-construction app targeted to users who are looking for lawyers.",
            "On a daily basis, people wouldn't need a lawyer unless they encountered some problem or have their own personal lawyer. When it comes to finding lawyers, people would either search them up on google or get recommended for one. Most times people don’t know how to find them, the expenses of hiring one, and if they’re trustworthy or not. This app will help clients narrow down their selections by their budget, the distance in travel (visiting them), and their legal needs.",
            "Josephine Chan - Product Owner\nChristian Concepcion - Scrum Master\nJack Egan - Head Developer"
    };

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }
}
