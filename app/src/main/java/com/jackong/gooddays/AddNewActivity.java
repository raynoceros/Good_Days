package com.jackong.gooddays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class AddNewActivity extends AppCompatActivity {

    MyDBHandler myDBHandler;
    Toolbar toolbar;
    TextView textView;
    Calendar calendar;
    DatePicker datePicker;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add New Event");
        myDBHandler = new MyDBHandler(this,null,null,1);
        textView = (TextView)findViewById(R.id.datesNameText);
        calendar = Calendar.getInstance();
        datePicker = (DatePicker) findViewById(R.id.datesText);
        checkBox = (CheckBox)findViewById(R.id.checkBox_repeat);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
        String dateString = sdf.format(calendar.getTime());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void add_event_to_db(View view) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        String date_end = sdf.format(calendar.getTime());
        long date = System.currentTimeMillis();
        String date_now = sdf.format(date);

        // calculation of days
        int howmany_days = 0;
        int _true = 1;
        int _false = 0;
        try {
            Date start_date = sdf.parse(date_now);
            Date end_date = sdf.parse(date_end);
            long diff = end_date.getTime() - start_date.getTime();
            int days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            String days_text = String.valueOf(days);
            howmany_days = Integer.parseInt(days_text);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // end
        if(checkBox.isChecked()){
            RecordedDates recordedDates = new RecordedDates(textView.getText().toString(),date_end,_true,howmany_days);
            myDBHandler.addDates(recordedDates);
        }
        else{
            RecordedDates recordedDates = new RecordedDates(textView.getText().toString(),date_end,_false,howmany_days);
            myDBHandler.addDates(recordedDates);
        }
        Toast.makeText(AddNewActivity.this, "Database has been updated", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
