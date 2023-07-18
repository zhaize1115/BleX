package com.eden.blex.callback;

import android.bluetooth.BluetoothGattCharacteristic;

public abstract class BleWriteCallback<T> {
    public abstract void onWriteSuccess(T device, BluetoothGattCharacteristic characteristic);

    public void onWriteFailed(T device, int failedCode) {
    }
}
