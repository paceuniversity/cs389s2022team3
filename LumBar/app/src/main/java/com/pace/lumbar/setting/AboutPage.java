package com.pace.lumbar.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.pace.lumbar.R;
import com.pace.lumbar.account.LoginPage;

public class AboutPage extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapter myAdapter;
    private ImageButton revertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myAdapter = new SlideAdapter(this);
        if(myAdapter!=null)
            viewPager.setAdapter(myAdapter);

        revertBtn = findViewById(R.id.revert);
        revertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutPage.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
}