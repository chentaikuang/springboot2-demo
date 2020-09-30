package xiaochen.event.listener;

import xiaochen.event.context.AbstractContextEvent;
import xiaochen.event.context.ContextRunEvent;

public class ContextRunEventListener implements ContextListener {
    @Override
    public void onApplicationEvent(AbstractContextEvent event) {
        if (event instanceof ContextRunEvent) {
            System.out.println("RUN：" + event.getTimestamp());
        }
    }
}
