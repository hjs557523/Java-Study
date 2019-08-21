/**
 * 具体装饰
 */
class ConcreteDecorator extends AbstractDecorator {
    
    public ConcreteDecorator(AbstractComponent component) {
        super(component);
    }

    public void operation() {
        super.operation();
        addFunction();
    }

    public void addFunction() {
        System.out.println("为具体构件增加新功能 addFunction()");
    }

}