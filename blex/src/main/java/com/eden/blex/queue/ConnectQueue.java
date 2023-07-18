package com.eden.blex.queue;

import androidx.annotation.NonNull;

import com.eden.blex.BleLog;
import com.eden.blex.model.BleDevice;
import com.eden.blex.queue.reconnect.DefaultReConnectHandler;

public final class ConnectQueue extends Queue {

    private static volatile ConnectQueue sInstance;

    private ConnectQueue() {
    }

    @NonNull
    public static ConnectQueue getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (ConnectQueue.class) {
            if (sInstance == null) {
                sInstance = new ConnectQueue();
            }
        }
        return sInstance;
    }

    @Override
    public void execute(RequestTask requestTask) {
        BleDevice device = requestTask.getDevices()[0];
        boolean reconnect = DefaultReConnectHandler.provideReconnectHandler().reconnect(device);
        BleLog.i("ConnectQueue", "正在重新连接设备:>>>>>>>result:" + reconnect + ">>>" + device.getBleName());
    }

}
