package com.eden.blex.callback;

import com.eden.blex.model.BleDevice;

public abstract class BleMtuCallback<T> {
    public void onMtuChanged(BleDevice device, int mtu, int status) {
    }
}
