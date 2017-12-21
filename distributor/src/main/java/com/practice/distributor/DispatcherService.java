package com.practice.distributor;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 12/21/2017.
 */
public class DispatcherService {

    private static final int MAX_THREAD_COUNT = 10;
    private static ExecutorService executorService;
    private static DispatcherService instance;

    private DispatcherService() {
        executorService = Executors.newFixedThreadPool(MAX_THREAD_COUNT);
    }

    public void pushJob(final Invokable invokable,final Object data) {
//        InvokerData invokerData = new InvokerData(invokable, data);
//        this.jobs.add(invokerData);
        if(!this.executorService.isShutdown() && !this.executorService.isTerminated()) {
            this.executorService.execute(new Runnable() {
                public void run() {
                    invokable.invoke(data);
                }
            });
        }
        else {
            throw new IllegalStateException("Dispatcher services are stopped");
        }
    }

    public static void initialize(){
        if(instance == null){
            synchronized (DispatcherService.class) {
                if(instance == null) {
                    instance = new DispatcherService();
                }
            }
        }
    }

    public static DispatcherService getInstance(){
        if(instance != null) {
            return instance;
        }
        throw new IllegalStateException("Dispatcher service is nto initialized");
    }

}
