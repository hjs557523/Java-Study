public class Client {
    // private Product1 p1;
    // private Product2 p2;
    // private AbstractFactory af;
    public static void main(String[] args) {
        Product1 p11, p12;
        Product2 p21, p22;
        AbstractFactory af1, af2;
        af1 = (AbstractFactory) new ConcreteFactory1();
        af2 = (AbstractFactory) new ConcreteFactory2();
        p11 = af1.newProduct1();
        p21 = af1.newProduct2();
        p12 = af2.newProduct1();
        p22 = af2.newProduct2();
    }
}