package com.eden.blex.request;

import com.eden.blex.Ble;
import com.eden.blex.BleRequestImpl;
import com.eden.blex.annotation.Implement;
import com.eden.blex.callback.BleMtuCallback;
import com.eden.blex.callback.wrapper.BleWrapperCallback;
import com.eden.blex.callback.wrapper.MtuWrapperCallback;
import com.eden.blex.model.BleDevice;

@Implement(MtuRequest.class)
public class MtuRequest<T extends BleDevice> implements MtuWrapperCallback<T> {

    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
    private BleMtuCallback<T> bleMtuCallback;

    public boolean setMtu(String address, int mtu, BleMtuCallback<T> callback) {
        this.bleMtuCallback = callback;
        return bleRequest.setMtu(address, mtu);
    }

    @Override
    public void onMtuChanged(T device, int mtu, int status) {
        if (null != bleMtuCallback) {
            bleMtuCallback.onMtuChanged(device, mtu, status);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onMtuChanged(device, mtu, status);
        }
    }
}
