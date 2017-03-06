package com.jackong.gooddays;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FrameLayout frameLayout;
    DatePicker datePicker;
    Button button;
    TextView textView;
    ListView listView;
    MyDBHandler myDBHandler;
    SimpleCursorAdapter simpleCursorAdapter;
    Fragment fragment;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
    long date_now = System.currentTimeMillis();
    String dateString = sdf.format(date_now);
    String dateString1 = sdf.format(date_now);
    protected void onCreate(Bundle savedInstanceState) {
        myDBHandler = new MyDBHandler(this,null,null,1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolBar);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        setSupportActionBar(toolbar);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new DaysFragment(),"Days");
        viewPagerAdapter.addFragments(new DCalculatorFragment(),"Date Calculator");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        drawerLayout =(DrawerLayout) findViewById(R.id.activity_main);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        frameLayout = (FrameLayout)findViewById(R.id.date_panel);
        listView = (ListView)findViewById(R.id.display_dates_listview);

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (tabLayout.getTabAt(1).isSelected()){
                FrameLayout frameLayout = (FrameLayout)findViewById(R.id.date_panel);
                FrameLayout frameLayout1 = (FrameLayout)findViewById(R.id.date_panel1);
                Calendar calendar;
                Calendar calendar1;
                calendar = Calendar.getInstance();
                datePicker = (DatePicker) findViewById(R.id.datePicker);
                calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                if(frameLayout.getVisibility() != View.VISIBLE && frameLayout1.getVisibility() != View.VISIBLE) {
                    super.onBackPressed();
                }
                else if(frameLayout.getVisibility() == View.VISIBLE){
                    calendar = Calendar.getInstance();
                    calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
                    String dateString = sdf.format(calendar.getTime());
                    button = (Button)findViewById(R.id.from_date);
                    button.setText("From ::  " + dateString);
                    frameLayout.setVisibility(View.GONE);
                    try {
                        Date start_date = sdf.parse(dateString);
                        Date end_date = sdf.parse(dateString1);
                        long diff = end_date.getTime() - start_date.getTime();

                        int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        String days_text = String.valueOf(days);
                        textView = (TextView)findViewById(R.id.howManyDays);
                        textView.setText(days_text);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else if(frameLayout1.getVisibility() == View.VISIBLE){
                    datePicker = (DatePicker) findViewById(R.id.datePicker1);
                    calendar1 = Calendar.getInstance();
                    calendar1.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
                    String dateString1 = sdf.format(calendar1.getTime());
                    button = (Button)findViewById(R.id.to_date);
                    button.setText("To ::  " + dateString1);
                    frameLayout1.setVisibility(View.GONE);
                    try {
                        Date start_date = sdf.parse(dateString);
                        Date end_date = sdf.parse(dateString1);
                        long diff = end_date.getTime() - start_date.getTime();
                        int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                        String days_text = String.valueOf(days);
                        textView = (TextView)findViewById(R.id.howManyDays);
                        textView.setText(days_text);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }


            }
            else{
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_action_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    public void hideAddButton (){
        ActionMenuItemView item = (ActionMenuItemView) findViewById(R.id.add_button);
        if (item != null){
            item.setVisibility(View.INVISIBLE);
        }
    }

    public void showAddButton() {
        ActionMenuItemView item = (ActionMenuItemView) findViewById(R.id.add_button);
        if (item != null){
            item.setVisibility(View.VISIBLE);
        }
    }


    public void days_onClick(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        viewPager.setCurrentItem(0);
    }
    public void date_calculator_onClick(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        viewPager.setCurrentItem(1);
    }

    public void settings_onClick(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        startActivity(new Intent(this,SettingActivity.class));
    }

    public void about_onClick(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        startActivity(new Intent(this,AboutActivity.class));
    }


    public void btn_from_date_onClick(View view) {
        frameLayout = (FrameLayout)findViewById(R.id.date_panel);
        frameLayout.setVisibility(View.VISIBLE);
    }

    public void btn_to_date_onClick(View view) {
        frameLayout = (FrameLayout)findViewById(R.id.date_panel1);
        frameLayout.setVisibility(View.VISIBLE);
    }

    public void add_onClick(MenuItem item) {
        startActivity(new Intent(this,AddNewActivity.class));
    }


}
