/**
 * 具体目标
 */
class ConcreteTarget extends AbstractTarget {
    public void notifyObserver() {
        System.out.println("具体目标发生改变");
        System.out.println("---------------");

        for (Object obs : observers) {
            ((AbstractObserver) obs).response();
        }
    }
}