/**
 * 具体构件
 */
class ConcreteComponent implements AbstractComponent{
    
    public ConcreteComponent() {
        System.out.println("创建具体构件1角色");
    }

    public void operation() {
        System.out.println("调用具体构件1角色方法 operation()");
    }
}