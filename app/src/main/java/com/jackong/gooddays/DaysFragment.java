package com.jackong.gooddays;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaysFragment extends Fragment {


    TextView textView;
    MyDBHandler myDBHandler;
    SimpleCursorAdapter simpleCursorAdapter;
    ListView listView;

    public DaysFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View myInflatedView = inflater.inflate(R.layout.fragment_days, container,false);
        myDBHandler = new MyDBHandler(getActivity(),null,null,1);
        myDBHandler.UpdateDB();
        LoadData(myInflatedView);
        return myInflatedView;
    }

    class ItemList implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id){
            ViewGroup vg = (ViewGroup)view;
            TextView dateName = (TextView)vg.findViewById(R.id.dates_name);
            TextView date = (TextView)vg.findViewById(R.id.end_date);
            TextView day = (TextView)vg.findViewById(R.id.dates_days);
            Intent appInfo = new Intent(getActivity(), Date_InfoActivity.class);
            //Toast.makeText(getActivity(),day.getText().toString(),Toast.LENGTH_SHORT).show();
            appInfo.putExtra("date_day", day.getText().toString());
            appInfo.putExtra("date_name", dateName.getText().toString());
            appInfo.putExtra("date", date.getText().toString());
            startActivity(appInfo);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            ((MainActivity)getActivity()).setActionBarTitle("Days");
            ((MainActivity)getActivity()).showAddButton();
        }
    }
    public void LoadData(View root){
        Cursor cursor;
        myDBHandler = new MyDBHandler(getActivity(),null,null,1);
        cursor = myDBHandler.getAllRows();
        String[] fromFieldNames = new String[] {myDBHandler.COLUMN_ID,myDBHandler.COLUMN_DATE_NAME,myDBHandler.COLUMN_DAYS, myDBHandler.COLUMN_DATE};
        int[] toViewIDs = new int[] {R.id.record_id,R.id.dates_name, R.id.dates_days, R.id.end_date};
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.display_listview_row,cursor,fromFieldNames,toViewIDs,0);
        listView = (ListView) root.findViewById(R.id.display_dates_listview);
        listView.setAdapter(simpleCursorAdapter);
        listView.setOnItemClickListener(new ItemList());
    }


}
