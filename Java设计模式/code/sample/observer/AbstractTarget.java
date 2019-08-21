import java.util.ArrayList;
import java.util.List;

/**
 * 抽象目标类
 */
abstract class AbstractTarget {
    
    //一个目标对应多个观察者
    protected List<AbstractObserver> observers = new ArrayList<AbstractObserver>();

    //增加观察者方法
    public void add(AbstractObserver observer) {
        observers.add(observer);
    }

    //删除观察者方法
    public void remove(AbstractObserver observer) {
        observers.remove(observer);
    }

    //通知观察者方法（抽象）
    public abstract void notifyObserver();
}