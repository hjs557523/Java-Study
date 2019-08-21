/**
 * 抽象工厂
 */
interface AbstractFactory {

    //笔记本电脑类产品
    public Product1 newProduct1();
    
    //电视机类产品
    public Product2 newProduct2();

}