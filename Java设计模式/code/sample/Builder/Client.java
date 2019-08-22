/**
 * 客户类
 */
public class Client {
    public static void main(String[] args) {
        AbstractBuilder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }
}