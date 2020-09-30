package xiaochen.event.listener;

import xiaochen.event.context.AbstractContextEvent;
import xiaochen.event.context.ContextStopEvent;

public class ContextStopEventListener implements ContextListener {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextStopEvent){
            System.out.println("STOP：" + event.getTimestamp());
        }
    }
}
