import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    interface IHello {
        void sayHello();
    }

    /**
     * 委托类/被代理类
     */
    static class Hello implements IHello {
        @Override
        public void sayHello(){
            System.out.println("hello world");
        }
    }

    /**
     * InvocationHandler接口的实现类
     */
    static class DynamicProxy implements InvocationHandler {
        Object originalObj;//委托类对象成员
        Object bind(Object originalObj){
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(
                originalObj.getClass().getClassLoader(), 
                originalObj.getClass().getInterfaces(), 
                this);//返回生成的代理类对象
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        //返回了一个实现IHello接口，并且代理了new Hello()实例行为的代理对象

        hello.sayHello();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }
}