interface Interface{
    public void foo();
}

/**委托类*/
class A implements Interface{
    public void foo(){
        System.out.println("Method foo() of class A!");
    }
}

/**代理类
 * 代理类能够很好地控制和辅助被代理类，并且可以增加额外的功能。而且一般来说
 * 代理类B和被代理类A都会实现同样的接口，这样对用户端(Consumer类)的代码没有任何影响，耦合很低
 */
class B implements Interface{
    public static long count = 0;
    public A a;
    public void foo(){
        a = new A();
        a.foo();
        count++; 
    }
}

/**用户*/
class Consumer{
    public static void consum(Interface i){
        i.foo();
    }
}

/**测试*/
public class TestProxy{
    public static void main(String[] args){
        Interface i = new B();
        Consumer.consum(i);
    }
}