package com.practice.example;

import com.practice.distributor.Invokable;
import com.practice.example.Data;

/**
 * Created by admin on 12/21/2017.
 */
public class InvokableService implements Invokable {
    public void invoke(Object object) {
        if(object instanceof Data)
            System.out.print(object.toString());
    }
}
