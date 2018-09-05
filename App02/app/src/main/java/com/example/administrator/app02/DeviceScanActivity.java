/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.administrator.app02;

import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Activity for scanning and displaying available Bluetooth LE devices.
 */
public class DeviceScanActivity extends ListActivity {
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    private static final int REQUEST_ENABLE_BT = 1;
    // 스캔은 10초야
    private static final long SCAN_PERIOD = 10000;

    // 생명주기에 의해 맨 처음 시작되죠
    // 스캔 안해
    // 내 블루투스가 BLE를 지원하는가? 블루투스를 지원하는가? 검사만 해
    // 그 후 블루투스 어댑터 생성
    // 끝!
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle(R.string.title_devices);
        mHandler = new Handler();

        // 내 블루투스가 BLE를 지원하는가? 확인한다
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            // 안된다면 "BLE는 지원 안댐!" 표시
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            // 그러고 끝내
            finish();
        }

        // 블루투스 어댑터를 생성(초기화)해
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        // 내 장치(기기)가 블루투스를 지원하는가? 확인한다.
        if (mBluetoothAdapter == null) {
            // 설마 안해? 그럼 "블루투스 지원안댐" 표시
            Toast.makeText(this, R.string.error_bluetooth_not_supported, Toast.LENGTH_SHORT).show();
            // 그러고 끝 싹
            finish();
            return;
        }
    }

    // 메인은 끝났고
    // 이건 onResume() 즉 앱 종료 후 다시 켰을 경우야
    // 다시 키면 다시 스캔되도록 짰어
    @Override
    protected void onResume() {
        super.onResume();
        // 블루투스 꺼져있네?
        if (!mBluetoothAdapter.isEnabled()) {
            // 그럼 요청창 띄운다
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            // 스타트액티비티뽈리졀트니깐 결과값까지 받아온다
            // enableBtIntent(첫 번째 인자)는 실행할 데이터가 담긴 객체
            // REQUEST_ENABLE_BT(두 번째 인자)는 어떤 요청인가 구별용, 이건 블루투스허용해달라는 요청이겟쥬
            // 결과값은 창이 종료될 떄 onActivityResult가 호출되면서 그 안에 담겨온다.
        }

        // 블루투스 켜져있네?
        // 그럼 리스트어댑터ㄱ
        mLeDeviceListAdapter = new LeDeviceListAdapter();
        setListAdapter(mLeDeviceListAdapter);
        // 스캔 활성화ㄱ
        scanLeDevice(true);
    }


    // 블루투스 요청 승인하면 결과값을 가져온다 .
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 1. requestCode : 어떤 액티비티에서 왔나
        // 2. resultCode : 어떤 버튼 눌렀나
        // 3. data : 새로운 창에서 보낸 인텐트
        // User chose not to enable Bluetooth.

        //  불루투스 다이얼로그 창으롭터 호출된건가?
        // AND
        // 그 창에서 아니오를 눌렀어?

        // 그렇다면 앱을 종료한다. 이거지
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }
        // 이 경우가 아니라면 계속 onResume 진행할거야
        super.onActivityResult(requestCode, resultCode, data);
    }

    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 스캔 시작 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // 스캔 주기가 지나갔어?
            // 그럼 스캔을 멈춰
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    invalidateOptionsMenu();
                    // onCreateOptionsMenu 메소드를 다시 호출해ㅔ
                }
            }, SCAN_PERIOD);
            // 스캔 시작
            mScanning = true;
            // START
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            // 스캔을 멈춤
            mScanning = false;
            // STOP
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        invalidateOptionsMenu();
        // onCreateOptionsMenu 메소드를 다시 호출해ㅔ

    }

    // 이건 스캔을 통해 찾은 디바이스를 담을 어댑터야\
    // 일반 어댑터랑 같아유
    private class LeDeviceListAdapter extends BaseAdapter {
        private ArrayList<BluetoothDevice> mLeDevices;
        private LayoutInflater mInflator;

        public LeDeviceListAdapter() {
            super();
            mLeDevices = new ArrayList<BluetoothDevice>();
            mInflator = DeviceScanActivity.this.getLayoutInflater();
        }

        public void addDevice(BluetoothDevice device) {
            if(!mLeDevices.contains(device)) {
                mLeDevices.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mInflator.inflate(R.layout.listitem_device, null);
                viewHolder = new ViewHolder();
                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
                viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            BluetoothDevice device = mLeDevices.get(i);
            final String deviceName = device.getName();
            if (deviceName != null && deviceName.length() > 0)
                viewHolder.deviceName.setText(deviceName);
            else
                viewHolder.deviceName.setText(R.string.unknown_device);
            viewHolder.deviceAddress.setText(device.getAddress());

            return view;
        }
    }

    // 콜백함수
    // 새로운 장치가 발견될 때마다 onLeScan을 호출한다
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    // 1. final BluetoothDevice device
                    // 이 첫 번째 인자에 검색된 장치의 정보가 들어온다.
                    // 정보가 들어오면?
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 리스트에 장치 추가
                            mLeDeviceListAdapter.addDevice(device);
                            // 리스트 갱신
                            mLeDeviceListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            };

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // 1. ListView : 리스트뷰 객체
        // 2. View : 부모 객체
        // 2. position : 클릭된 아이템 위치
        // 3. id : 그 아이템의 고유 번호
        final BluetoothDevice device = mLeDeviceListAdapter.getDevice(position);
        if (device == null) return;
        final Intent intent = new Intent(this, DeviceControlActivity.class);
        // 그래서 DeviceControlActivity로 인텐트를 만들고
        // 추가로 클릭된 디바이스의 정보를 담아 ( NAME, ADDRESS )
        intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_NAME, device.getName());
        intent.putExtra(DeviceControlActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
        if (mScanning) {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
            // 그리고 스캔을 중지해
            mScanning = false;
        }
        // 새로운 액티비티를 띄워
        // 그게 DeviceControlActivity지
        startActivity(intent);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 메뉴 클릭이벤트야
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        // 스캔중이라면!
        if (!mScanning) {
            // 스캔 버튼만 보이게
            menu.findItem(R.id.menu_stop).setVisible(false);
            menu.findItem(R.id.menu_scan).setVisible(true);
            menu.findItem(R.id.menu_refresh).setActionView(null);
        } else {
            // 스캔 중이 아니야
            // 그럼 스탑버튼만 보여
            // 추가로 프로그레스바 넣었어
            menu.findItem(R.id.menu_stop).setVisible(true);
            menu.findItem(R.id.menu_scan).setVisible(false);
            menu.findItem(R.id.menu_refresh).setActionView(
                    R.layout.actionbar_indeterminate_progress);
        }
        return true;
    }

    // 메뉴 아이템 선택
    // 스캔 - 스탑 버튼 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                // 스캔버튼 누르먄
                mLeDeviceListAdapter.clear();
                // 어댑터 초기화하고
                // 스캔 활성화ㄱ
                scanLeDevice(true);
                break;
            case R.id.menu_stop:
                // 스탑버튼 누르면
                // 스캔 비활성화
                scanLeDevice(false);
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanLeDevice(false);
        mLeDeviceListAdapter.clear();
    }



    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }
}