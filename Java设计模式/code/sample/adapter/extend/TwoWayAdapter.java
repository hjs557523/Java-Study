/**
 * 双向适配器
 */
class TwoWayAdapter implements TwoWayTarget, TwoWayAdaptee {

    private TwoWayAdaptee adaptee;
    private TwoWayTarget target;

    public TwoWayAdapter(TwoWayAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    public TwoWayAdapter(TwoWayTarget target) {
        this.target = target;
    }

    public void request() {
        adaptee.specificRequest();
    }

    public void specificRequest() {
        target.request();
    }

}