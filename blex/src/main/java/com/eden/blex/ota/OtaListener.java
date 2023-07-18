package com.eden.blex.ota;

public interface OtaListener {
    void onWrite();

    void onChange(byte[] data);
}
