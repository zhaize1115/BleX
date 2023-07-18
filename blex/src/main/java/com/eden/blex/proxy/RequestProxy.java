package com.eden.blex.proxy;

import android.content.Context;

import com.eden.blex.BleLog;
import com.eden.blex.request.ConnectRequest;
import com.eden.blex.request.DescriptorRequest;
import com.eden.blex.request.MtuRequest;
import com.eden.blex.request.NotifyRequest;
import com.eden.blex.request.ReadRequest;
import com.eden.blex.request.ReadRssiRequest;
import com.eden.blex.request.Rproxy;
import com.eden.blex.request.ScanRequest;
import com.eden.blex.request.WriteRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RequestProxy implements InvocationHandler {
    private static final String TAG = "RequestProxy";
    private Object receiver;

    private RequestProxy() {
    }

    public static RequestProxy newProxy() {
        return new RequestProxy();
    }

    //Bind the delegate object and return the proxy class
    public Object bindProxy(Context context, Object tar) {
        this.receiver = tar;
        //绑定委托对象，并返回代理类
        BleLog.d(TAG, "bindProxy: " + "Binding agent successfully");
        Rproxy.init(ConnectRequest.class, MtuRequest.class,
                NotifyRequest.class, ReadRequest.class,
                ReadRssiRequest.class, ScanRequest.class,
                WriteRequest.class, DescriptorRequest.class);
        return Proxy.newProxyInstance(
                tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(receiver, args);
    }
}
