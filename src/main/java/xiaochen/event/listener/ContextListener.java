package xiaochen.event.listener;


import xiaochen.event.context.AbstractContextEvent;

/**
 * ContextListener接口被具体的容器生命周期事件监听器实现
 * @param <T>
 */
public interface ContextListener<T extends AbstractContextEvent> extends EventListener {
    void onApplicationEvent(T event);
}
