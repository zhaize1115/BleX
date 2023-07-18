package com.eden.blex.callback;

import android.bluetooth.BluetoothGattDescriptor;

public abstract class BleReadDescCallback<T> {

    public void onDescReadSuccess(T device, BluetoothGattDescriptor descriptor) {
    }

    public void onDescReadFailed(T device, int failedCode) {
    }

}
