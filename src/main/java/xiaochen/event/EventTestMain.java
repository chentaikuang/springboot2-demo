package xiaochen.event;

import xiaochen.event.context.ContextRunEvent;
import xiaochen.event.context.ContextStartEvent;
import xiaochen.event.context.ContextStopEvent;
import xiaochen.event.listener.ContextRunEventListener;
import xiaochen.event.listener.ContextStartEventListener;
import xiaochen.event.listener.ContextStopEventListener;
import xiaochen.event.multicaster.SimpleApplicationEventMulticaster;

public class EventTestMain {

    public static void main(String[] args) {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster(true);
        eventMulticaster.addListener(new ContextStopEventListener());
        eventMulticaster.addListener(new ContextStartEventListener());
        eventMulticaster.addListener(new ContextRunEventListener());

        for (int nm = 0; nm < 10; nm++) {
            eventMulticaster.publishEvent(new ContextStartEvent());
            eventMulticaster.publishEvent(new ContextRunEvent());
            eventMulticaster.publishEvent(new ContextStopEvent());
//            join(1000);
//            System.out.println("---------------");
        }
        join();

        //todo 发布事件后给已存在的监听者都发送，存在资源浪费：可否根据事件类型、标识，广播给目标监听者即可
    }

    private static void join(int... i) {
        try {
            Thread.currentThread().join(i == null || i.length == 0 ? 0 : i[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
