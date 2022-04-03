package com.pace.lumbar.aboutpage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.pace.lumbar.R;

public class AboutPage extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myAdapter = new SlideAdapter(this);
        viewPager.setAdapter(myAdapter);
    }
}