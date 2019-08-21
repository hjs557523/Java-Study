/**
 * 抽象处理者
 */
abstract class AbstractHandler {
    
    private AbstractHandler next; //后继处理者

    public void setNext(AbstractHandler next) {
        this.next = next;
    }

    public AbstractHandler getNext() {
        return this.next;
    }

    //处理请求的方法
    public abstract void handleRequest(String request);
}