package com.eden.blex.request;

import android.bluetooth.BluetoothGattDescriptor;

import com.eden.blex.Ble;
import com.eden.blex.BleRequestImpl;
import com.eden.blex.annotation.Implement;
import com.eden.blex.callback.BleReadDescCallback;
import com.eden.blex.callback.BleWriteDescCallback;
import com.eden.blex.callback.wrapper.BleWrapperCallback;
import com.eden.blex.callback.wrapper.DescWrapperCallback;
import com.eden.blex.model.BleDevice;

import java.util.UUID;

@Implement(DescriptorRequest.class)
public class DescriptorRequest<T extends BleDevice> implements DescWrapperCallback<T> {

    private final BleWrapperCallback<T> bleWrapperCallback = Ble.options().getBleWrapperCallback();
    private final BleRequestImpl<T> bleRequest = BleRequestImpl.getBleRequest();
    private BleReadDescCallback<T> bleReadDescCallback;
    private BleWriteDescCallback<T> bleWriteDescCallback;

    public boolean readDes(T device, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, BleReadDescCallback<T> callback) {
        this.bleReadDescCallback = callback;
        return bleRequest.readDescriptor(device.getBleAddress(), serviceUUID, characteristicUUID, descriptorUUID);
    }

    public boolean writeDes(T device, byte[] data, UUID serviceUUID, UUID characteristicUUID, UUID descriptorUUID, BleWriteDescCallback<T> callback) {
        this.bleWriteDescCallback = callback;
        return bleRequest.writeDescriptor(device.getBleAddress(), data, serviceUUID, characteristicUUID, descriptorUUID);
    }


    @Override
    public void onDescReadSuccess(T device, BluetoothGattDescriptor descriptor) {
        if (bleReadDescCallback != null) {
            bleReadDescCallback.onDescReadSuccess(device, descriptor);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescReadSuccess(device, descriptor);
        }
    }

    @Override
    public void onDescReadFailed(T device, int failedCode) {
        if (bleReadDescCallback != null) {
            bleReadDescCallback.onDescReadFailed(device, failedCode);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescReadFailed(device, failedCode);
        }
    }

    @Override
    public void onDescWriteSuccess(T device, BluetoothGattDescriptor descriptor) {
        if (bleWriteDescCallback != null) {
            bleWriteDescCallback.onDescWriteSuccess(device, descriptor);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescWriteSuccess(device, descriptor);
        }
    }

    @Override
    public void onDescWriteFailed(T device, int failedCode) {
        if (bleWriteDescCallback != null) {
            bleWriteDescCallback.onDescWriteFailed(device, failedCode);
        }

        if (bleWrapperCallback != null) {
            bleWrapperCallback.onDescWriteFailed(device, failedCode);
        }
    }
}
