package com.clj.blesample.operation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.clj.blesample.MainScreenActivity;
import com.clj.blesample.R;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;

import org.greenrobot.eventbus.EventBus;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CharacteristicOperationFragment extends Fragment {

    public static final int PROPERTY_READ = 1;
    public static final int PROPERTY_WRITE = 2;
    public static final int PROPERTY_WRITE_NO_RESPONSE = 3;
    public static final int PROPERTY_NOTIFY = 4;
    public static final int PROPERTY_INDICATE = 5;
    private boolean alreadyHappened=false;

    private LinearLayout layout_container;
    private final List<String> childList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_characteric_operation, null);

        layout_container = (LinearLayout) v.findViewById(R.id.layout_container);
        return v;
    }

    private void initView(View v) {
        layout_container = (LinearLayout) v.findViewById(R.id.layout_container);
        Spinner spinner = (Spinner) v.findViewById(R.id.analogSpinner);
    }

    public void showData() {
        final BleDevice bleDevice = ((OperationActivity) getActivity()).getBleDevice();
        final BluetoothGattCharacteristic characteristic = ((OperationActivity) getActivity()).getCharacteristic();
        final int charaProp = ((OperationActivity) getActivity()).getCharaProp();
        String child = characteristic.getUuid().toString() + String.valueOf(charaProp);

        for (int i = 0; i < layout_container.getChildCount(); i++) {
            layout_container.getChildAt(i).setVisibility(View.VISIBLE);
        }
        if (childList.contains(child)) {
            layout_container.findViewWithTag(bleDevice.getKey() + characteristic.getUuid().toString() + charaProp).setVisibility(View.VISIBLE);
        } else {
            childList.add(child);

            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_characteric_operation, null);
            view.setTag(bleDevice.getKey() + characteristic.getUuid().toString() + charaProp);
            LinearLayout layout_add = (LinearLayout) view.findViewById(R.id.layout_add);
            final TextView txt = (TextView) view.findViewById(R.id.txt);



            switch (charaProp) {
                case PROPERTY_READ: {

                }
                break;

                case PROPERTY_WRITE: {
                    View view_add = LayoutInflater.from(getActivity()).inflate(R.layout.layout_characteric_operation_et, null);

                    //yazma kısmı buraya galiba


                    layout_add.addView(view_add);


                }
                break;

                case PROPERTY_WRITE_NO_RESPONSE: {
                }
                break;

                case PROPERTY_NOTIFY: {



                    BleManager.getInstance().setMtu(bleDevice, 250, new BleMtuChangedCallback() {
                        @Override
                        public void onSetMTUFailure(BleException exception) {

                        }

                        @Override
                        public void onMtuChanged(int mtu) {

                            BleManager.getInstance().notify(
                                    bleDevice,
                                    characteristic.getService().getUuid().toString(),
                                    characteristic.getUuid().toString(),
                                    new BleNotifyCallback() {

                                        @Override
                                        public void onNotifySuccess() {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                }
                                            });
                                        }

                                        @Override
                                        public void onNotifyFailure(final BleException exception) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    //addText(txt, exception.toString());
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCharacteristicChanged(final byte[] data) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    int x =characteristic.getValue().length;
                                                    String sharedFact = HexUtil.formatHexString(characteristic.getValue());
                                                    byte[] bytes = hexStringToByteArray(sharedFact);
                                                    String st = new String(bytes, StandardCharsets.UTF_8);
                                                    EventBus.getDefault().postSticky(new MessageEvent(st));

                                                }
                                            });
                                        }
                                    });

                        }
                    });



                }
                break;


                case PROPERTY_INDICATE: {

                }
                break;
            }

            layout_container.addView(view);
        }
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    private void runOnUiThread(Runnable runnable) {
        if (isAdded() && getActivity() != null)
            getActivity().runOnUiThread(runnable);
    }

    private void addText(TextView textView, String content) {
        textView.append(content);
        textView.append("\n");
        int offset = textView.getLineCount() * textView.getLineHeight();
        if (offset > textView.getHeight()) {
            textView.scrollTo(0, offset - textView.getHeight());
        }
    }

    public class MessageEvent {

        public String mMessage;

        public MessageEvent(String message) {
            mMessage = message;
        }

        public String getMessage() {
            return mMessage;
        }
    }

    public static class EventBuss {

        public static void main(String[] args) {
            EventBuss eventBuss = new EventBuss();
            EventBus.getDefault().register(eventBuss);
            //EventBus.getDefault().post("hello?");
            // EventBus.getDefault().unregister(eventBuss);
        }



    }

    public static byte[] hexStringToByteArray(String hex) {
        int l = hex.length();
        byte[] data = new byte[l / 2];
        for (int i = 0; i < l; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }


}