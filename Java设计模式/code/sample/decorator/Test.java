public class Test {
    public static void main(String[] args) {
        AbstractComponent component = new ConcreteComponent();
        component.operation();
        System.out.println("---------------------------------");
        AbstractComponent component2 = new ConcreteDecorator(component);
        component2.operation();
    }
}