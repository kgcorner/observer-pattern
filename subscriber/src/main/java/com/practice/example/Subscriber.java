package com.practice.example;
import com.practice.distributor.Dispatcher;
import com.practice.distributor.Subscribevable;
/**
 * Created by admin on 12/21/2017.
 */
public class Subscriber implements Subscribevable {

    private InvokableService service = new InvokableService();

    public void subscribe() {
        Dispatcher.getInstance().subscriber(service, SUBSCRIBER_METHODS.SERVICE_DO_SERVICE.toString());
    }
}
