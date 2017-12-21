package com.practice.distributor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 12/21/2017.
 */
public class Dispatcher {
    private static Dispatcher instance;
    Map<String, Invokable> subscriber;

    private Dispatcher() {
        this.subscriber = new HashMap<String, Invokable>();
    }

    public static Dispatcher getInstance() {
        if(instance == null) {
            throw new IllegalStateException("Dispatcher's not properly instantiated");
        }
        return instance;
    }

    public void subscriber(Invokable invokable, String method) {
        if(this.subscriber != null) {
            this.subscriber.put(method, invokable);
        }
        else
            throw new IllegalStateException("Dispatcher's not properly instantiated");
    }

    public void Dispatch(String method, Object data) {
        if(this.subscriber != null) {
            Invokable invokable = this.subscriber.get(method);
            if(invokable != null) {
                invokable.invoke(data);
            }
        }
        else
            throw new IllegalStateException("Dispatcher's not properly instantiated");
    }

    public void Dispatch(String method, Object data, boolean invokeAsynchronously) {
        if(invokeAsynchronously) {
            if (this.subscriber != null) {
                Invokable invokable = this.subscriber.get(method);
                if (invokable != null) {
                    DispatcherService.getInstance().pushJob(invokable, data);
                }

            } else
                throw new IllegalStateException("Dispatcher's not properly instantiated");
        }else {
            Dispatch(method, data);
        }
    }

    public static void initialize() {
        if(instance == null) {
            instance = new Dispatcher();
        }
        DispatcherService.initialize();
    }
}
