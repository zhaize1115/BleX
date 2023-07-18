package com.eden.blex.callback.wrapper;

public interface ReadRssiWrapperCallback<T> {
    void onReadRssiSuccess(T device, int rssi);
}
