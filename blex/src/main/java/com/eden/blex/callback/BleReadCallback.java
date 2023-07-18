package com.eden.blex.callback;

import android.bluetooth.BluetoothGattCharacteristic;

public abstract class BleReadCallback<T> {

    public void onReadSuccess(T dedvice, BluetoothGattCharacteristic characteristic) {
    }

    public void onReadFailed(T device, int failedCode) {
    }

}
