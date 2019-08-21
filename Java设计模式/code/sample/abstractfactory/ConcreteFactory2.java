/**
 * 华为工厂
 */
public class ConcreteFactory2 implements AbstractFactory{
    public Product1 newProduct1() {
        System.out.println("华为公司生产出：笔记本电脑");
        return new ConcreteProduct12();
    }

    public Product2 newProduct2() {
        System.out.println("华为公司生产出：电视");
        return new ConcreteProduct22();
    }
}