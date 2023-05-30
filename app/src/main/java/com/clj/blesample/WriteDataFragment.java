package com.clj.blesample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;


public class WriteDataFragment extends Fragment {

    TableRow tableRow,tableRow2,tableRow3,tableRow4,tableRow28,settingsConfirm1,tableRow5,tableRow6,tableRow7,tableRow11,tableRow12,tableRow13,advancedConfirm;
    LinearLayout layout;
    TableRow tableRow29,tableRow31;
    ImageButton arrowButton1,arrowButton2,arrowButton4;
    CardView cardView,cardView2,cardView4;
    Switch sw1,sw2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_write_data, container, false);

        layout = v.findViewById(R.id.layoutTable);

        sw1=v.findViewById(R.id.simpleSwitch1);
        sw2=v.findViewById(R.id.simpleSwitch2);

        tableRow2=v.findViewById(R.id.tableRow2);
        tableRow3=v.findViewById(R.id.tableRow3);
        tableRow4 = v.findViewById(R.id.tableRow4);
        tableRow5=v.findViewById(R.id.tableRow5);
        tableRow6=v.findViewById(R.id.tableRow6);
        tableRow7 = v.findViewById(R.id.tableRow7);
        tableRow11 = v.findViewById(R.id.tableRow11);
        tableRow12 = v.findViewById(R.id.tableRow12);
        tableRow13 = v.findViewById(R.id.tableRow13);
        tableRow28 = v.findViewById(R.id.tableRow28);
        tableRow29 = v.findViewById(R.id.tableRow29);
        tableRow31 = v.findViewById(R.id.tableRow31);
        settingsConfirm1 = v.findViewById(R.id.settingsConfirm1);

        cardView = v.findViewById(R.id.cardView);
        cardView2= v.findViewById(R.id.cardView2);
        cardView4=v.findViewById(R.id.cardView4);

        Button settingsConfirm =(Button) v.findViewById(R.id.settingsConfirm);
        Button advancedConfirm =(Button) v.findViewById(R.id.advancedConfirm);


        arrowButton1 = v.findViewById(R.id.arrow_button1);
        arrowButton2 = v.findViewById(R.id.arrow_button2);
        arrowButton4 = v.findViewById(R.id.arrow_button4);


        final EditText rangeET = (EditText) v.findViewById(R.id.rangeET);
        final EditText maxRangeET = (EditText) v.findViewById(R.id.maxRangeET);
        final EditText lowPercentET = (EditText) v.findViewById(R.id.lowPercentET);
        final EditText highPercentET = (EditText) v.findViewById(R.id.highPercentET);

        final TextView fastLevelTV = (TextView) v.findViewById(R.id.fastLevelTV);
        final TextView agitatedTV = (TextView) v.findViewById(R.id.agitatedTV);
        final TextView rangeTV = (TextView) v.findViewById(R.id.rangeTV);
        final TextView dampingTimeTV = (TextView) v.findViewById(R.id.maxRangeTV);
        final TextView rangeVal = (TextView) v.findViewById(R.id.rangeVal);
        final TextView dampingTimeVal = (TextView) v.findViewById(R.id.dampingTimeVal);
        final TextView minTV = (TextView) v.findViewById(R.id.minTV);
        final TextView minVal = (TextView) v.findViewById(R.id.minVal);
        final TextView maxTV = (TextView) v.findViewById(R.id.maxTV);
        final TextView maxVal = (TextView) v.findViewById(R.id.maxVal);
        final TextView versionTV = (TextView) v.findViewById(R.id.versionTV);
        final TextView versionVal = (TextView) v.findViewById(R.id.versionVal);
        final TextView resetTV = (TextView) v.findViewById(R.id.resetTV);
        final TextView lowPercentTV = (TextView) v.findViewById(R.id.lowPercentTV);
        final TextView highPercentTV = (TextView) v.findViewById(R.id.highPercentTV);

        arrowButton1.setOnClickListener(v112 -> {

            int x = (tableRow2.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int y = (tableRow3.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int z = (tableRow4.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int q = (tableRow28.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int a = (settingsConfirm1.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;


            TransitionManager.beginDelayedTransition(layout,new AutoTransition());

            tableRow2.setVisibility(x);
            tableRow3.setVisibility(x);
            tableRow4.setVisibility(x);
            tableRow28.setVisibility(x);
            settingsConfirm1.setVisibility(x);

            if(tableRow2.getVisibility()==View.VISIBLE)
            {
                arrowButton1.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            }
            else if (tableRow2.getVisibility()==View.GONE)
            {
                arrowButton1.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }

        });

        arrowButton2.setOnClickListener(v112 -> {
            int x = (tableRow5.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int y = (tableRow6.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int z = (tableRow7.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;




            TransitionManager.beginDelayedTransition(layout,new AutoTransition());

            tableRow5.setVisibility(x);
            tableRow6.setVisibility(x);
            tableRow7.setVisibility(x);

            if(tableRow5.getVisibility()==View.VISIBLE)
            {
                arrowButton2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            }
            else if (tableRow5.getVisibility()==View.GONE)
            {
                arrowButton2.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }


        });

        arrowButton4.setOnClickListener(v112 -> {
            int x = (tableRow11.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int y = (tableRow12.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int z = (tableRow13.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;
            int a = (tableRow31.getVisibility()== View.GONE)? View.VISIBLE:View.GONE;

            TransitionManager.beginDelayedTransition(layout,new AutoTransition());

            tableRow11.setVisibility(x);
            tableRow12.setVisibility(x);
            tableRow13.setVisibility(x);
            tableRow31.setVisibility(x);

            if(tableRow11.getVisibility()==View.VISIBLE)
            {
                arrowButton4.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            }
            else if (tableRow11.getVisibility()==View.GONE)
            {
                arrowButton4.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }

            //analogSpinner
            final Spinner resetSpinner = (Spinner) v.findViewById(R.id.resetSpinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.analogSpinner, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            resetSpinner.setAdapter(adapter);





        });

        settingsConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        getActivity())
                        .setTitle("Check")  // Setting Dialog Title
                        .setMessage("Do you want to change these values?")// Setting Dialog Message
                        .setCancelable(false)
                        .create();

                // Setting OK Button
                alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        StringBuilder minRange = new StringBuilder(rangeET.getText().toString());
                        StringBuilder maxRange = new StringBuilder(maxRangeET.getText().toString());
                        variables.setMinRange(minRange.toString());
                        variables.setMaxRange(maxRange.toString());
                        rangeET.getText().clear();
                        maxRangeET.getText().clear();
                        rangeET.setHint(variables.getMinRange());
                        maxRangeET.setHint(variables.getMaxRange());


                    }
                });
                alertDialog.setButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rangeET.getText().clear();
                        maxRangeET.getText().clear();
                    }
                });
                alertDialog.show();

            }
        });

        advancedConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        getActivity())
                        .setTitle("Check")  // Setting Dialog Title
                        .setMessage("Do you want to change these values?")// Setting Dialog Message
                        .setCancelable(false)
                        .create();

                // Setting OK Button
                alertDialog.setButton2("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {





                    }
                });
                alertDialog.setButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rangeET.getText().clear();
                        maxRangeET.getText().clear();
                    }
                });
                alertDialog.show();

            }
        });


        return v;
    }
}