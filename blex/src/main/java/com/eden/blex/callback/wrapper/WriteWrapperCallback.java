package com.eden.blex.callback.wrapper;

import android.bluetooth.BluetoothGattCharacteristic;

public interface WriteWrapperCallback<T> {
    void onWriteSuccess(T device, BluetoothGattCharacteristic characteristic);
    void onWriteFailed(T device, int failedCode);
}
