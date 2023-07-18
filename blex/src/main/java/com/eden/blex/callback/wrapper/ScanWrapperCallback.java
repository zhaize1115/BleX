package com.eden.blex.callback.wrapper;

import android.bluetooth.BluetoothDevice;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.eden.blex.model.ScanRecord;

public interface ScanWrapperCallback {
    /**
     * Start the scan
     */
    void onStart();

    /**
     * Stop scanning
     */
    void onStop();

    /**
     * Scan to device
     *
     * @param device     ble device object
     * @param rssi       rssi
     * @param scanRecord Bluetooth radio package
     */
    void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord);

    /**
     * errorCode=1;Fails to start scan as BLE scan with the same settings is already started by the app.
     * errorCode=2;Fails to start scan as app cannot be registered.
     * errorCode=3;Fails to start scan due an internal error
     * errorCode=4;Fails to start power optimized scan as this feature is not supported
     *
     * @param errorCode 扫描错误码
     */
    void onScanFailed(int errorCode);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void onParsedData(BluetoothDevice device, ScanRecord scanRecord);
}
