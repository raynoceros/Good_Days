package com.jackong.gooddays;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class DCalculatorFragment extends Fragment {

    FrameLayout frameLayout;

    public DCalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate(R.layout.fragment_date_calculator, container,false);

        // Set the Text to try this out
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE");
        String dateString = sdf.format(date);
        Button fromDate = (Button) myInflatedView.findViewById(R.id.from_date);
        fromDate.setText("From ::  " + dateString);
        Button toDate  = (Button) myInflatedView.findViewById(R.id.to_date);
        toDate.setText("To ::  " + dateString);
        frameLayout = (FrameLayout) myInflatedView.findViewById(R.id.date_panel);
        frameLayout.setVisibility(View.GONE);
        frameLayout = (FrameLayout) myInflatedView.findViewById(R.id.date_panel1);
        frameLayout.setVisibility(View.GONE);
        return myInflatedView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            ((MainActivity)getActivity()).setActionBarTitle("Date Calculator");
            ((MainActivity)getActivity()).hideAddButton();
        }
    }



}
