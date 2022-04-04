package com.pace.lumbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class AboutPage extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myAdapter = new SlideAdapter(this);
        if(myAdapter!=null)
            viewPager.setAdapter(myAdapter);
    }
}