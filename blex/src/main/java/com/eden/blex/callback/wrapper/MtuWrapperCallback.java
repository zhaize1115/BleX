package com.eden.blex.callback.wrapper;

public interface MtuWrapperCallback<T> {

    void onMtuChanged(T device, int mtu, int status);

}
