package com.eden.blex.request;

import com.eden.blex.BleRequestImpl;
import com.eden.blex.annotation.Implement;
import com.eden.blex.callback.BleReadRssiCallback;
import com.eden.blex.callback.wrapper.ReadRssiWrapperCallback;
import com.eden.blex.model.BleDevice;

@Implement(ReadRssiRequest.class)
public class ReadRssiRequest<T extends BleDevice> implements ReadRssiWrapperCallback<T> {

    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
    private BleReadRssiCallback<T> readRssiCallback;

    public boolean readRssi(T device, BleReadRssiCallback<T> callback) {
        this.readRssiCallback = callback;
        boolean result = false;
        if (bleRequest != null) {
            result = bleRequest.readRssi(device.getBleAddress());
        }
        return result;
    }

    @Override
    public void onReadRssiSuccess(T device, int rssi) {
        if (readRssiCallback != null) {
            readRssiCallback.onReadRssiSuccess(device, rssi);
        }
    }
}
