/**
 * 客户类
 */
public class Client {
    public static void main(String[] args) {
        //组装责任链
        AbstractHandler handler1 = new ConcreteHandler1();
        AbstractHandler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        //提交请求
        handler1.handleRequest("two");
     }
}