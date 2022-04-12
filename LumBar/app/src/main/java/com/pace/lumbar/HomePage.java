package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.pace.lumbar.fragments.ExploreFragment;
import com.pace.lumbar.fragments.HomeFragment;
import com.pace.lumbar.fragments.MatchesFragment;
import com.pace.lumbar.fragments.ProfileFragment;
import com.pace.lumbar.fragments.adapters.CustomViewPager;
import com.pace.lumbar.fragments.adapters.ViewPagerAdapter;

public class HomePage extends AppCompatActivity {

    private TabLayout tabLayout;
    private CustomViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTabs();
    }

    private void setUpTabs(){
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new HomeFragment(), "");
        vpAdapter.addFragment(new MatchesFragment(), "");
        vpAdapter.addFragment(new ExploreFragment(), "");
        vpAdapter.addFragment(new ProfileFragment(), "");
        viewPager.setAdapter(vpAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_favorite_border_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_search_24);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_account_circle_24);

        viewPager.disableScroll(true);
    }

    public void likePressed(View view) {
        Toast toast = Toast.makeText(this, R.string.like_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void dislikePressed(View view) {
        Toast toast = Toast.makeText(this, R.string.dislike_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void chatPressed(View view) {
        Toast toast = Toast.makeText(this, R.string.chat_message,
                Toast.LENGTH_SHORT);
        toast.show();
    }
}