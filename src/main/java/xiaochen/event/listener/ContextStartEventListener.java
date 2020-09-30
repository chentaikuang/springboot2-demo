package xiaochen.event.listener;

import xiaochen.event.context.AbstractContextEvent;
import xiaochen.event.context.ContextStartEvent;

public class ContextStartEventListener implements ContextListener {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextStartEvent) {
            System.out.println("START：" + event.getTimestamp());
        }
    }
}
