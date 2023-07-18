package com.eden.blex.request;

import android.bluetooth.BluetoothGattCharacteristic;

import com.eden.blex.Ble;
import com.eden.blex.BleRequestImpl;
import com.eden.blex.annotation.Implement;
import com.eden.blex.callback.BleReadCallback;
import com.eden.blex.callback.wrapper.BleWrapperCallback;
import com.eden.blex.callback.wrapper.ReadWrapperCallback;
import com.eden.blex.model.BleDevice;

import java.util.UUID;

@Implement(ReadRequest.class)
public class ReadRequest<T extends BleDevice> implements ReadWrapperCallback<T> {

    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
    private BleReadCallback<T> bleReadCallback;

    public boolean read(T device, BleReadCallback<T> callback) {
        this.bleReadCallback = callback;
        return bleRequest.readCharacteristic(device.getBleAddress());
    }

    public boolean readByUuid(T device, UUID serviceUUID, UUID characteristicUUID, BleReadCallback<T> callback) {
        this.bleReadCallback = callback;
        return bleRequest.readCharacteristicByUuid(device.getBleAddress(), serviceUUID, characteristicUUID);
    }

    @Override
    public void onReadSuccess(T device, BluetoothGattCharacteristic characteristic) {
        if (bleReadCallback != null) {
            bleReadCallback.onReadSuccess(device, characteristic);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onReadSuccess(device, characteristic);
        }
    }

    @Override
    public void onReadFailed(T device, int failedCode) {
        if (bleReadCallback != null) {
            bleReadCallback.onReadFailed(device, failedCode);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onReadFailed(device, failedCode);
        }
    }
}
