package com.eden.blex.model;

public abstract class BleFactory<T extends BleDevice> {

    public T create(String address, String name) {
        return (T) new BleDevice(address, name);
    }

}
