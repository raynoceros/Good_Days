package com.jackong.gooddays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Date_InfoActivity extends AppCompatActivity {
    Toolbar toolbar;
    MyDBHandler myDBHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date__info);
        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Good Days");
        Intent intent = getIntent();
        String dateName = intent.getStringExtra("date_name");
        String dateDay = intent.getStringExtra("date_day");
        String date = intent.getStringExtra("date");
        TextView textView = (TextView)findViewById(R.id.act_date_name);
        textView.setText(dateName);
        TextView textView1 = (TextView)findViewById(R.id.act_date_days);
        textView1.setText(dateDay);
        TextView textView2 = (TextView)findViewById(R.id.act_date_info);
        textView2.setText(date);
        myDBHandler = new MyDBHandler(this,null,null,1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public void delete_onClick(MenuItem item) {
        TextView textView = (TextView)findViewById(R.id.act_date_name);
        String name = textView.getText().toString();
        TextView textView1 = (TextView)findViewById(R.id.act_date_info);
        String date = textView1.getText().toString();
        myDBHandler.deleteDates(name, date);
        Toast.makeText(this,"Successfully delete", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_action_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
