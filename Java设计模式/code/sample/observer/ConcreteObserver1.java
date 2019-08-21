/**
 * 具体观察者1
 */
class ConcreteObserver1 implements AbstractObserver{

    public void response() {
        System.out.println("具体观察者1完成响应！");
    }
}