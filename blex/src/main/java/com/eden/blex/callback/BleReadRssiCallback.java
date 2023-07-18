package com.eden.blex.callback;

public abstract class BleReadRssiCallback<T> {

    public void onReadRssiSuccess(T device, int rssi) {
    }
}
