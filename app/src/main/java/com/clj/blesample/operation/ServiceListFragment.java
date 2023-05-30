package com.clj.blesample.operation;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.clj.blesample.R;
import com.clj.fastble.BleManager;
import com.clj.fastble.data.BleDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class ServiceListFragment extends Fragment {

    private TextView txt_name, txt_mac;
    private ResultAdapter mResultAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_list, null);
        initView(v);
        showData();

        return v;
    }

    private void initView(View v) {
        txt_name = (TextView) v.findViewById(R.id.txt_name);
        txt_mac = (TextView) v.findViewById(R.id.txt_mac);

        mResultAdapter = new ResultAdapter(getActivity());
        final ListView listView_device = (ListView) v.findViewById(R.id.list_service);
        listView_device.setAdapter(mResultAdapter);


        /*listView_device.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


        listView_device.setOnItemClickListener((parent, view, position, id) -> {

        });

        BleDevice bleDevice = ((OperationActivity) getActivity()).getBleDevice();
        String name = bleDevice.getName();
        String mac = bleDevice.getMac();
        BluetoothGatt gatt = BleManager.getInstance().getBluetoothGatt(bleDevice);

        /*txt_name.setText(String.valueOf(getActivity().getString(R.string.name) + name));
        txt_mac.setText(String.valueOf(getActivity().getString(R.string.mac) + mac));*/

        mResultAdapter.clear();
        for (BluetoothGattService service : gatt.getServices()) {
            mResultAdapter.addResult(service);
        }

        listView_device.post(new Runnable() {
            @Override
            public void run() {
                listView_device.clearFocus();
                mResultAdapter.notifyDataSetChanged();
                listView_device.requestFocusFromTouch();
                listView_device.setSelection(3);
                BluetoothGattService service = mResultAdapter.getItem(3);
                ((OperationActivity) Objects.requireNonNull(getActivity())).setBluetoothGattService(service);
                ((OperationActivity) getActivity()).changePage(1);            }
        });
    }

    private void showData() {

    }

    private class ResultAdapter extends BaseAdapter {

        private Context context;
        private final List<BluetoothGattService> bluetoothGattServices;

        ResultAdapter(Context context) {
            this.context = context;
            bluetoothGattServices = new ArrayList<>();
        }

        void addResult(BluetoothGattService service) {
            bluetoothGattServices.add(service);
        }

        void clear() {
            bluetoothGattServices.clear();
        }

        @Override
        public int getCount() {
            return bluetoothGattServices.size();
        }

        @Override
        public BluetoothGattService getItem(int position) {
            if (position > bluetoothGattServices.size())
                return null;
            return bluetoothGattServices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = View.inflate(context, R.layout.adapter_service, null);
                holder = new ViewHolder();
                convertView.setTag(holder);
                holder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
                holder.txt_uuid = (TextView) convertView.findViewById(R.id.txt_uuid);
                holder.txt_type = (TextView) convertView.findViewById(R.id.txt_type);
            }

            BluetoothGattService service = bluetoothGattServices.get(position);
            String uuid = service.getUuid().toString();


            holder.txt_title.setText(String.valueOf(getActivity().getString(R.string.service) + "(" + position + ")"));
            holder.txt_uuid.setText(uuid);
            holder.txt_type.setText(getActivity().getString(R.string.type));

            return convertView;
        }

        class ViewHolder {
            TextView txt_title;
            TextView txt_uuid;
            TextView txt_type;
        }
    }
    private void setListviewSelection(final ListView list, final int pos) {
        list.post(() -> {
            list.setSelection(pos);
            View v = list.getChildAt(pos);
            if (v != null)
            {
                v.requestFocus();
            }
        });
    }



}
