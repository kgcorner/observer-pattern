package com.practice.example;

import com.practice.distributor.Dispatcher;

/**
 * Created by admin on 12/21/2017.
 */
public class Service {
    public void doService(Object data) {
        Dispatcher.getInstance().Dispatch(SUBSCRIBER_METHODS.SERVICE_DO_SERVICE.toString(), data, true);
    }

}
