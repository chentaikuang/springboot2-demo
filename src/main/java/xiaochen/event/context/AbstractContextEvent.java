package xiaochen.event.context;

/**
 * 容器事件的基本抽象类:代表各个类型的事件存放容器
 */
public class AbstractContextEvent implements Event {


    private final long timestamp = System.currentTimeMillis();

    public final long getTimestamp() {
        return this.timestamp;
    }
}
