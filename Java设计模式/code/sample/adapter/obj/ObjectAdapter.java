/**
 * 对象适配器模式
 */
class ObjectAdapter implements Target {
    
    private Adaptee adaptee;
    
    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.specificRequest();
    }

}