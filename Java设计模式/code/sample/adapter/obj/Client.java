/**
 * 客户端测试
 */

public class Client {

    public static void main(String[] args) {
        System.out.println("客户端进行对象适配器模式测试：");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}