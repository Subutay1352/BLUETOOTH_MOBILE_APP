package com.clj.blesample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.blesample.operation.CharacteristicOperationFragment;
import com.clj.blesample.operation.OperationActivity;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Objects;


public class FlowFragment extends Fragment {

    public TextView medium_txt,medium_val_txt,signalout_txt,signalout_val_txt,signalstrength_txt,signalstrength_val_txt,
    currentDistance_txt,currentDistance_val_txt,levelperc_txt,levelperc_val_txt,minVal_txt,minVal_perc_txt,currentVal_txt,
    currentVal_perc_txt,maxVal_txt,maxVal_perc_txt;

    public ImageView imageView;


    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void onEvent(CharacteristicOperationFragment.MessageEvent event) {

        String message = event.getMessage().toString();

        String[] arrStrx = new String[5];

        arrStrx[1] = message.substring(message.indexOf("+") + 1, message.indexOf("*"));
        arrStrx[2] = message.substring(message.indexOf("*") + 1, message.indexOf("-"));
        arrStrx[3] = message.substring(message.indexOf("-") + 1, message.indexOf("/"));
        arrStrx[4] = message.substring(message.indexOf("/") + 1, message.indexOf("?"));



        if(Objects.equals(arrStrx[1], "94") && Objects.equals(arrStrx[4], "48") )
        {
            float x = (float) (Math.round((Float.parseFloat(arrStrx[2])/1000)*100.0)/100.0);
            String y = Float.toString(x);
            arrStrx[2]=y;
            variables.setDistance(arrStrx[2]);
            variables.setSignalStrenght(arrStrx[3]);
            try {
                signalstrength_val_txt.setText(variables.getSignalStrenght());
            }
            catch (Exception e)
            {
                signalstrength_val_txt.setText("---");
            }

            float currentDistanceF = Float.parseFloat(variables.getDistance());
            currentDistanceF= (float) ((Math.round(currentDistanceF*100.0))/100.0);

            currentDistance_val_txt.setText(variables.getDistance()+"m");

            int maxVal = Integer.parseInt(variables.getMaxRange());
            int minVal=  Integer.parseInt(variables.getMinRange());

            float xper = (float)((currentDistanceF-minVal)/(maxVal-minVal))*100;
            xper= (float) (Math.round(((xper+25)/6.25)*100)/100);


            signalout_val_txt.setText(Float.toString(xper)+"mA");

            float percentage = (float) ((currentDistanceF/maxVal)*100.0);
            if(percentage>100) percentage=100.0f;
            if(percentage<0) percentage=0.0f;
            levelperc_val_txt.setText(percentage+"%");

            currentVal_txt.setText(variables.getDistance()+"m");

            float currentValF= (float) ((Float.parseFloat(variables.getDistance())/maxVal)*100);
            currentVal_perc_txt.setText(currentValF+"%");

            if(currentValF<10 && currentValF>=0)
                imageView.setImageResource(R.drawable.ten);
            else if(currentValF<20 && currentValF>=10)
                imageView.setImageResource(R.drawable.twnty);
            else if(currentValF<30 && currentValF>=20)
                imageView.setImageResource(R.drawable.thirty);
            else if(currentValF<40 && currentValF>=30)
                imageView.setImageResource(R.drawable.fourty);
            else if(currentValF<50 && currentValF>=40)
                imageView.setImageResource(R.drawable.fifty);
            else if(currentValF<60 && currentValF>=50)
                imageView.setImageResource(R.drawable.sixty);
            else if(currentValF<70 && currentValF>=60)
                imageView.setImageResource(R.drawable.seventy);
            else if(currentValF<80 && currentValF>=70)
                imageView.setImageResource(R.drawable.eighty);
            else if(currentValF<90 && currentValF>=80)
                imageView.setImageResource(R.drawable.ninety);
            else if( currentValF>=90)
                imageView.setImageResource(R.drawable.hundred);


            minVal_txt.setText(variables.getMinRange()+"m");
            maxVal_txt.setText(variables.getMaxRange()+"m");
        }

    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_flow, container, false);
        variables.setMaxRange("10");
        variables.setMinRange("0");

        initView(v);
        OnAttact(getActivity());
        return v;
    }

     private void initView(View v)
     {

         maxVal_txt =v.findViewById(R.id.signalStrengthVal4);
         minVal_txt=v.findViewById(R.id.signalStrengthVal2);
         currentVal_txt=v.findViewById(R.id.signalStrengthVal6);
         currentVal_perc_txt=v.findViewById(R.id.signalStrengthVal7);
         medium_val_txt=v.findViewById(R.id.mediumVal);
         currentDistance_val_txt=v.findViewById(R.id.distanceVal);
         signalout_val_txt=v.findViewById(R.id.signalVal);
         levelperc_val_txt=v.findViewById(R.id.levelVal);
         signalstrength_val_txt=v.findViewById(R.id.signalStrengthVal);
         imageView = v.findViewById(R.id.imageView);

     }

    private void readRssi(BleDevice bleDevice) {
        BleManager.getInstance().readRssi(bleDevice, new BleRssiCallback() {
            @Override
            public void onRssiFailure(BleException exception) {
                variables.setRssi(0);
            }

            @Override
            public void onRssiSuccess(int rssi) {
                variables.setRssi(rssi);
            }
        });
    }



    public void OnAttact(Activity activity) {
        super.onStart();
        EventBus.getDefault().register(this);
    }
}