public class Client {
    public static void main(String[] args) {
        AbstractTarget target = new ConcreteTarget();
        AbstractObserver obs1 = new ConcreteObserver1();
        AbstractObserver obs2 = new ConcreteObserver2();
        target.add(obs1);
        target.add(obs2);
        target.notifyObserver();
    }
}