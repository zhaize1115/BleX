package com.eden.blex.callback;

public abstract class BleWriteEntityCallback<T> {
    public abstract void onWriteSuccess();

    public abstract void onWriteFailed();

    public void onWriteProgress(double progress) {
    }

    public void onWriteCancel() {
    }
}
