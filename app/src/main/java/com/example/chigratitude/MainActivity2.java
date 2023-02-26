package com.example.chigratitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView siderBarNav;
    private DrawerLayout mDrawerLayout;
    private ViewPager2 viewPager2;
    private Handler sliderhandler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(MainActivity2.this,paymentActivity3.class);
                startActivity(paymentIntent);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        siderBarNav = findViewById(R.id.sidebar_navigation);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        siderBarNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_contacts) {
                    Intent startContactIntent = new Intent(MainActivity2.this, contactActivity.class);
                    startActivity(startContactIntent);
                    return true;
                }
                if (item.getItemId() == R.id.nav_share) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "hello");
                    intent.putExtra(Intent.EXTRA_TEXT, "click the link");
                    startActivity(intent.createChooser(intent, "share via"));
                    return true;
                }
                if (item.getItemId() == R.id.nav_home) {

                    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                    mDrawerLayout.closeDrawers();
                    return true;

                }
                if (item.getItemId() == R.id.nav_info) {
                    Intent startInfoIntent = new Intent(MainActivity2.this,InfoActivity.class);
                    startActivity(startInfoIntent);
                    return true;
                }

                return false;
            }
        });
        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        List<Slideritem> slideritems = new ArrayList<>();
        slideritems.add(new Slideritem(R.drawable.grace1));
        slideritems.add(new Slideritem(R.drawable.grace3));
        slideritems.add(new Slideritem(R.drawable.grace10));
        slideritems.add(new Slideritem(R.drawable.grace17));
        slideritems.add(new Slideritem(R.drawable.grace6));
        slideritems.add(new Slideritem(R.drawable.grace7));
        slideritems.add(new Slideritem(R.drawable.grace11));
        slideritems.add(new Slideritem(R.drawable.grace12));
        slideritems.add(new Slideritem(R.drawable.grace4));
        slideritems.add(new Slideritem(R.drawable.grace15));
        slideritems.add(new Slideritem(R.drawable.grace8));
        slideritems.add(new Slideritem(R.drawable.grace9));
        slideritems.add(new Slideritem(R.drawable.grace2));
        slideritems.add(new Slideritem(R.drawable.grace13));
        slideritems.add(new Slideritem(R.drawable.grace14));

        viewPager2.setAdapter(new SliderAdapter(slideritems,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r =1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderhandler.removeCallbacks(sliderRunnable);
                sliderhandler.postDelayed(sliderRunnable,2000);
            }
        });



    }
    private  Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {

            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.action_Exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }





}