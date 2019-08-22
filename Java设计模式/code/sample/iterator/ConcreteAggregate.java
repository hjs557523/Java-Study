import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 具体聚合
 */
class ConcreteAggregate implements AbstractAggregate {
    private List<Object> list = new ArrayList<Object>();

    public void add(Object obj) {
        list.add(obj);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public AbstractIterator getIterator() {
       return (new ConcreteIterator(list));
    }
}