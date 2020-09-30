package xiaochen.event.multicaster;

import xiaochen.event.context.AbstractContextEvent;
import xiaochen.event.listener.ContextListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimpleApplicationEventMulticaster implements ApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(boolean sync) {
        this.sync = sync;
    }

    private boolean sync = false;

    private List<ContextListener> contextListenerList = new ArrayList<>();
    private Object listenerLock = contextListenerList;

    private Executor executor = new ThreadPoolExecutor(1, 2,
            300, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(5),
            new InnerRejectHandler());


    @Override
    public void addListener(ContextListener contextListener) {
        synchronized (listenerLock) {
            contextListenerList.add(contextListener);
        }
    }

    @Override
    public void removeListener(ContextListener contextListener) {
        synchronized (this.listenerLock) {
            contextListenerList.remove(contextListener);
        }
    }

    @Override
    public void removeAllListener() {
        synchronized (this.listenerLock) {
            contextListenerList.clear();
        }
    }

    @Override
    public void publishEvent(AbstractContextEvent abstractContextEvent) {
        contextListenerList.forEach(listener -> {
            if (sync) {
                executor.execute(() -> {
                    listener.onApplicationEvent(abstractContextEvent);
                });
            } else {
                listener.onApplicationEvent(abstractContextEvent);
            }
        });
    }

    class InnerRejectHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            System.out.println(r.getClass().getName());
            Thread newThread = new Thread(r, "new-thread-run");
            newThread.start();
        }
    }
}

