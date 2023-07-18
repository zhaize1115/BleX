package com.eden.blex.request;

import android.bluetooth.BluetoothGattCharacteristic;

import com.eden.blex.Ble;
import com.eden.blex.BleRequestImpl;
import com.eden.blex.annotation.Implement;
import com.eden.blex.callback.BleNotifyCallback;
import com.eden.blex.callback.wrapper.BleWrapperCallback;
import com.eden.blex.callback.wrapper.NotifyWrapperCallback;
import com.eden.blex.model.BleDevice;

import java.util.UUID;

@Implement(NotifyRequest.class)
public class NotifyRequest<T extends BleDevice> implements NotifyWrapperCallback<T> {

    private static final String TAG = "NotifyRequest";
    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
    private BleNotifyCallback<T> notifyCallback;

    public void notify(T device, boolean enable, BleNotifyCallback<T> callback) {
        notifyCallback = callback;
        bleRequest.setCharacteristicNotification(device.getBleAddress(), enable);
    }

    public void notifyByUuid(T device, boolean enable, UUID serviceUUID, UUID characteristicUUID, BleNotifyCallback<T> callback) {
        notifyCallback = callback;
        bleRequest.setCharacteristicNotificationByUuid(device.getBleAddress(), enable, serviceUUID, characteristicUUID);
    }

    @Deprecated
    public void cancelNotify(T device, BleNotifyCallback<T> callback) {
        notifyCallback = callback;
        bleRequest.setCharacteristicNotification(device.getBleAddress(), false);
    }

    @Override
    public void onChanged(final T device, final BluetoothGattCharacteristic characteristic) {
        if (null != notifyCallback) {
            notifyCallback.onChanged(device, characteristic);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onChanged(device, characteristic);
        }
    }

    @Override
    public void onNotifySuccess(final T device) {
        if (null != notifyCallback) {
            notifyCallback.onNotifySuccess(device);
        }
        if (bleWrapperCallback != null) {
            bleWrapperCallback.onNotifySuccess(device);
        }
    }

    @Override
    public void onNotifyFailed(T device, int failedCode) {
        if (null != notifyCallback) {
            notifyCallback.onNotifyFailed(device, failedCode);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onNotifyFailed(device, failedCode);
        }
    }

    @Override
    public void onNotifyCanceled(T device) {
        if (null != notifyCallback) {
            notifyCallback.onNotifyCanceled(device);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onNotifyCanceled(device);
        }
    }
}
