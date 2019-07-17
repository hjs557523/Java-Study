import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;





interface Interface{
    public void foo();
}

/**委托类*/
class A implements Interface{
    public void foo(){
        System.out.println("Method foo() of class A!");
    }
}

/**
 * 这是Proxy要动态生成的B类
 */
// class B implements Interface{
//     public static long count = 0;
//     public A a;
//     public void foo(){
//         a = new A();
//         a.foo();
//         count++; 
//     }
// }

/**用户*/
class Consumer{
    public static void consum(Interface i){
        i.foo();
    }
}


/**
 * newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
 * param1: loader, 是为了生成 B 类的 Class 对象。作用是根据一组类的字节码 byte[] 直接生成这个类的 Class 对象。
 * param2: interfaces，由委托类实现的接口的 Class 对象数组。主要是包含了最重要的代理类需要实现的接口方法的信息。
 * param3: h,一个实现了 InvocationHandler 接口的对象。InvocationHandler 接口在 java.lang.reflect 包里。最主要的就是定义了 invoke( ) 方法。
 *         它就是假设在已经动态生成了最后的 proxy 代理对象，以及所有接口定义的方法 Method 对象以及方法的参数的情况下，定义我们要怎么调用这些方法的地方。
 */

class DynamicProxyHandler implements InvocationHandler{
    private Object proxied;
    public DynamicProxyHandler(Object proxied){
        this.proxied = proxied;
    }
    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable{
        try {
            return method.invoke(proxied, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class TestDynamicProxy{
    public static void main(String args[]){
        A a = new A();
        //直接把A类对象a当参数传进去，就动态产生一个代理类对象
        Interface proxy = (Interface)Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class<?>[]{Interface.class}, new DynamicProxyHandler(a));
        Consumer.consum(proxy);
    }
}