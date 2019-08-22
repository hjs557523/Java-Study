import java.util.List;

/**
 * 具体迭代器
 */
class ConcreteIterator implements AbstractIterator {
    private List<Object> list;
    private int index = -1;
    public ConcreteIterator(List<Object> list) {
        this.list = list;
    }

    public boolean hasNext() {
        if (index < list.size() - 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public Object first() {
        index = 0;
        Object obj = list.get(index);
        return obj;
    }

    public Object next() {
        Object obj = null;
        if (this.hasNext()) {
            obj = list.get(++index);
        }
        return obj;
    }
}