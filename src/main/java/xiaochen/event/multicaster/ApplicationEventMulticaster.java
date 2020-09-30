package xiaochen.event.multicaster;

import xiaochen.event.context.AbstractContextEvent;
import xiaochen.event.listener.ContextListener;

public interface ApplicationEventMulticaster {

    void addListener(ContextListener contextListener);

    void removeListener(ContextListener contextListener);

    void removeAllListener();

    void publishEvent(AbstractContextEvent abstractContextEvent);
}
