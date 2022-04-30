package com.pace.lumbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.pace.lumbar.adapters.CustomViewPager;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private CustomViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //Initialize and Assign Variable
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        //Set Home selected
//        bottomNavigationView.setSelectedItemId(R.id.home);
//
//        //Perform Item selected listener
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                switch(item.getItemId()){
//                    case R.id.match:
//                        startActivity(new Intent(getApplicationContext(),
//                                ChatActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//
//                    case R.id.home:
//                        return true;
//
//                    case R.id.profile:
//                        startActivity(new Intent(getApplicationContext(),
//                                ProfileFragment.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                }
//
//                return false;
//            }
//        });

        //setUpTabs();
    }

//    private void setUpTabs(){
////        tabLayout = findViewById(R.id.tabLayout);
////        viewPager = findViewById(R.id.viewPager);
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        vpAdapter.addFragment(new HomeFragment(), "");
//        vpAdapter.addFragment(new MatchesFragment(), "");
//        //vpAdapter.addFragment(new ExploreFragment(), "");
//        //vpAdapter.addFragment(new ProfileFragment(), "");
//        viewPager.setAdapter(vpAdapter);
//
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_favorite_border_24);
//        //tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_search_24);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_account_circle_24);
//
//        viewPager.disableScroll(true);
//    }

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