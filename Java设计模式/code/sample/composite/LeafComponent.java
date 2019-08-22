/**
 * 树叶构件
 */
class LeafComponent implements AbstractComponent {
    private String name;
    
    public LeafComponent(String name) {
        this.name = name;
    }

    //空实现或抛异常
    public void add(AbstractComponent c) {}

    //空实现或抛异常
    public void remove(AbstractComponent c) {}

    //空实现或抛异常
    public AbstractComponent getChild(int i) { return null;}


    public void operation() {
        System.out.println("树叶" + name + ": 被访问！");
    }
}