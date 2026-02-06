package com.saskpolytech.CST000503804.MainAcitvity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.saskpolytech.CST000503804.Adapter.FitnessPagerAdapter;
import com.saskpolytech.CST000503804.Fragment.ActivityLogFragment;
import com.saskpolytech.CST000503804.Fragment.GoalsFragment;
import com.saskpolytech.CST000503804.Fragment.HealthStatusCalculator;
import com.saskpolytech.CST000503804.Notification.NotificationScheduler;
import com.saskpolytech.CST000503804.R;

public class FitnessTrackingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FitnessPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_tracking);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        pagerAdapter = new FitnessPagerAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new HealthStatusCalculator(), "Health Status");
        pagerAdapter.addFragment(new ActivityLogFragment(), "Activity Log");
        pagerAdapter.addFragment(new GoalsFragment(), "Goals");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        long notificationTimeInMillis = System.currentTimeMillis() + (60 * 1000);
        NotificationScheduler.scheduleNotification(getApplicationContext(), notificationTimeInMillis);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
