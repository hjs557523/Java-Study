/**
 * 小米工厂
 */
public class ConcreteFactory1 implements AbstractFactory{
    public Product1 newProduct1() {
        System.out.println("小米公司生产出：笔记本电脑");
        return new ConcreteProduct11();
    }

    public Product2 newProduct2() {
        System.out.println("小米公司生产出：电视");
        return new ConcreteProduct21();
    }
}