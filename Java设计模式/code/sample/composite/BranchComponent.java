import java.util.ArrayList;
import java.util.List;

/**
 * 树枝构件
 */
class BranchComponent implements AbstractComponent {
    private List<AbstractComponent> children = new ArrayList<AbstractComponent>();
    
    public void add(AbstractComponent c) {
        children.add(c);
    }

    public void remove(AbstractComponent c) {
        children.remove(c);
    }

    public AbstractComponent getChild(int i) {
        return children.get(i);
    }

    public void operation() {
        for (Object obj : children) {
            ((AbstractComponent) obj).operation();
        }
    }

}