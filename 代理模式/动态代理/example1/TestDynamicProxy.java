import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;

interface Interface {
    public void foo();
}

/** 委托类/被代理类 */
class A implements Interface {
    public void foo() {
        System.out.println("Method foo() of class A!");
    }
}

/**
 * 静态代理方式 这是Proxy要动态生成的代理B类，这里改为采用动态代理方式，故注销
 */
// class B implements Interface{
// public static long count = 0;
// public A a;
// public void foo(){
// a = new A();
// a.foo();
// count++;
// }
// }

/** 用户 */
class Consumer {
    public static void consum(Interface i) {
        i.foo();
    }
}

/**
 * 作用：定义InvocationHandler接口的实现类
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;// 被代理类对象成员

    /**
     * 作用：通过构造函数获得参数传进来的被代理类对象
     * 
     * @param proxied 注入被代理类对象成员，即代理类/委托类对象a
     */
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    /**
     * 作用：实现InvocationHandler接口的invoke方法。它就是假设已经动态生成最后的proxy代理对象，
     * 以及所有接口定义的方法Method对象和方法的参数情况下，定义我们要怎么调用这些方法。即在该方
     * 法内定义最主要的业务逻辑。
     * 
     * @param proxy  假设已经动态生成了最后的prxoy代理对象b
     * @param method 所有接口定义的方法Method对象
     * @param args   方法Method对象的所有参数
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(proxied, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

/**
 * newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) 
 * param1: loader, 是为了生成 B 类的 Class 对象。作用是根据一组类的字节码 byte[] 直接生成这个类的 Class 对象。
 * param2: interfaces，由委托类/被代理类实现的接口的 Class 对象数组。主要是包含了最重要的代理类需要实现的接口方法的信息。 
 * param3: h,一个实现了 InvocationHandler 接口的对象。其内的invoke()方法定义了代理类的实现的接口方法的实际操作
 */
public class TestDynamicProxy {
    public static void main(String[] args) {
        A a = new A();
        // 直接把A类对象a当参数传进去，就动态产生一个代理类对象
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class<?>[] { Interface.class }, 
                new DynamicProxyHandler(a)
                );
        Consumer.consum(proxy);
    }
}