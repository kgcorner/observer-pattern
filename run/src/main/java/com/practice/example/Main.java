package com.practice.example;

import com.practice.distributor.Dispatcher;
import com.practice.distributor.Subscribevable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12/21/2017.
 */
public class Main {
    private static List<String> subscriberServices;

    public static void main(String[] a) {
        Dispatcher.initialize();
        populateSubscriber();
        for(String subscriberService : subscriberServices) {
            try {
                Subscribevable subscribevable = (Subscribevable) Class.forName(subscriberService).newInstance();
                subscribevable.subscribe();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        Data data = new Data(1, "Done");
        Service service = new Service();
        service.doService(data);
    }

    public static void populateSubscriber() {
        subscriberServices = new ArrayList<String>();
        subscriberServices.add("com.practice.example.Subscriber");
    }
}
