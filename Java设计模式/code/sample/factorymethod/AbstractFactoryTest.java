public class AbstractFactoryTest {

    //抽象产品：提供产品接口
    interface Product {
        public void show();
    }


    //具体产品1：实现抽象产品
    class ConcreteProduct1 implements Product {
        public void show() {
            System.out.println("具体产品1展示");
        }
    }


    //具体产品2：实现抽象产品
    class ConcreteProduct2 implements Product {
        public void show() {
            System.out.println("具体产品2展示");
        }
    }


    //抽象工厂：提供产品的生成方法
    interface AbstractFactory{
        public Product newProduct();
    }


    //具体工厂1：实现产品1生产方法
    class ConcreteFactory implements AbstractFactory {
        public Product newProduct() {
            System.out.println("具体工厂1生产出：产品1");
            return new ConcreteProduct1();
        }
    }


    //具体工厂2：实现产品2生产方法
    class ConcreteFactory2 implements AbstractFactory {
        public Product newProduct() {
            System.out.println("具体工厂2生产出：产品2");
            return new ConcreteProduct2();
        }
    }


    public static void main(String[] args) {
        try {
           Product a;
           AbstractFactory af;

           // 若四个内部实现类都声明为static，本static方法中可直接new ConcreteFactory()。否则那就必须先实例化外部类，再new

           af = (AbstractFactory) new AbstractFactoryTest().new ConcreteFactory();
           a = af.newProduct();
           a.show();
        } catch (Exception e) {
            
        }
    }

}